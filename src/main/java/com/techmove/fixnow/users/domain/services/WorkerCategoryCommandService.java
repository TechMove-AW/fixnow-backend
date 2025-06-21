package com.techmove.fixnow.users.domain.services;

import com.techmove.fixnow.users.domain.model.aggregates.WorkerCategory;
import com.techmove.fixnow.users.domain.model.commands.CreateWorkerCategoryCommand;

import java.util.Optional;

public interface WorkerCategoryCommandService {
    Optional<WorkerCategory> handle(CreateWorkerCategoryCommand command);
}
