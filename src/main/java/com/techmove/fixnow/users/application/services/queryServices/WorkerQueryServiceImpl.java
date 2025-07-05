package com.techmove.fixnow.users.application.services.queryServices;

import com.techmove.fixnow.users.domain.model.entities.Worker;
import com.techmove.fixnow.users.domain.model.queries.GetWorkerByIdQuery;
import com.techmove.fixnow.users.domain.model.queries.GetWorkersByCategoryIdQuery;
import com.techmove.fixnow.users.domain.services.WorkerQueryService;
import com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerQueryServiceImpl implements WorkerQueryService {
    
    private final WorkerRepository workerRepository;

    public WorkerQueryServiceImpl(WorkerRepository workerRepository) {
        this.workerRepository = workerRepository;
    }

    @Override
    public Optional<Worker> handle(GetWorkerByIdQuery query) {
        return workerRepository.findById(query.workerId());
    }

    @Override
    public List<Worker> handle(GetWorkersByCategoryIdQuery query) {
        return workerRepository.findByWorkerCategoryId(query.categoryId());
    }
}