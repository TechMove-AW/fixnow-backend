package com.techmove.fixnow.users.application.services.queryServices;

import com.techmove.fixnow.users.domain.model.aggregates.WorkerCategory;
import com.techmove.fixnow.users.domain.model.queries.GetAllWorkerCategoriesQuery;
import com.techmove.fixnow.users.domain.model.queries.GetWorkerCategoryByIdQuery;
import com.techmove.fixnow.users.domain.model.queries.GetWorkerCategoryBySlugQuery;
import com.techmove.fixnow.users.domain.services.WorkerCategoryQueryService;
import com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories.WorkerCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerCategoryQueryServiceImpl implements WorkerCategoryQueryService {
    private final WorkerCategoryRepository workerCategoryRepository;

    public WorkerCategoryQueryServiceImpl(
        WorkerCategoryRepository workerCategoryRepository
    ){
        this.workerCategoryRepository = workerCategoryRepository;
    }

    @Override
    public Optional<List<WorkerCategory>> handle(GetAllWorkerCategoriesQuery query) {
        return Optional.of(workerCategoryRepository.findAll());
    }

    @Override
    public Optional<WorkerCategory> handle(GetWorkerCategoryBySlugQuery query) {
        return this.workerCategoryRepository.findBySlug(query.slug());
    }

    @Override
    public Optional<WorkerCategory> handle(GetWorkerCategoryByIdQuery query) {
        return this.workerCategoryRepository.findById(query.id());
    }
}
