package com.techmove.fixnow.users.application.services.commandServices;

import com.techmove.fixnow.users.domain.model.aggregates.WorkerCategory;
import com.techmove.fixnow.users.domain.model.commands.CreateWorkerCategoryCommand;
import com.techmove.fixnow.users.domain.services.WorkerCategoryCommandService;
import com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories.WorkerCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerCategoryCommandServiceImpl implements WorkerCategoryCommandService {
    private final WorkerCategoryRepository workerCategoryRepository;

    public WorkerCategoryCommandServiceImpl(
            WorkerCategoryRepository workerCategoryRepository
    ){
        this.workerCategoryRepository = workerCategoryRepository;
    }

    @Override
    public Optional<WorkerCategory> handle(CreateWorkerCategoryCommand command) {
        var workerCategory = new WorkerCategory(command);
        if(this.workerCategoryRepository.existsBySlug(workerCategory.getSlug())){
            throw new IllegalArgumentException("Category with slug exists!");
        }
        this.workerCategoryRepository.save(workerCategory);
        return Optional.of(workerCategory);
    }
}
