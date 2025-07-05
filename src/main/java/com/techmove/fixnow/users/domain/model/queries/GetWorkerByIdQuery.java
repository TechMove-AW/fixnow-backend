package com.techmove.fixnow.users.domain.model.queries;

public record GetWorkerByIdQuery(Long workerId) {
    public GetWorkerByIdQuery {
        if (workerId == null) {
            throw new IllegalArgumentException("Worker ID cannot be null");
        }
    }
}