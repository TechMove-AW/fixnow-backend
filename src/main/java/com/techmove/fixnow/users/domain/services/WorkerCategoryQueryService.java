package com.techmove.fixnow.users.domain.services;

import com.techmove.fixnow.users.domain.model.aggregates.WorkerCategory;
import com.techmove.fixnow.users.domain.model.queries.GetAllWorkerCategoriesQuery;
import com.techmove.fixnow.users.domain.model.queries.GetWorkerCategoryByIdQuery;
import com.techmove.fixnow.users.domain.model.queries.GetWorkerCategoryBySlugQuery;

import java.util.List;
import java.util.Optional;

public interface WorkerCategoryQueryService {
    Optional<List<WorkerCategory>> handle(GetAllWorkerCategoriesQuery query);
    Optional<WorkerCategory> handle(GetWorkerCategoryBySlugQuery query);
    Optional<WorkerCategory> handle(GetWorkerCategoryByIdQuery query);
}
