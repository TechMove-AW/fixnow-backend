package com.techmove.fixnow.users.domain.model.queries;

import java.util.UUID;

public record GetWorkerByIdQuery(UUID workerId) {
    public GetWorkerByIdQuery {
        if (workerId == null) {
            throw new IllegalArgumentException("Worker ID cannot be null");
        }
    }
}