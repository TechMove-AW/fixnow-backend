package com.techmove.fixnow.users.domain.services;

import com.techmove.fixnow.users.domain.model.entities.Worker;
import com.techmove.fixnow.users.domain.model.queries.GetWorkerByIdQuery;

import java.util.Optional;

public interface WorkerQueryService {
    Optional<Worker> handle(GetWorkerByIdQuery query);
}