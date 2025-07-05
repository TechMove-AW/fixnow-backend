package com.techmove.fixnow.users.domain.model.queries;

public record GetWorkersByCategoryIdQuery(Long categoryId) {
    public GetWorkersByCategoryIdQuery {
        if (categoryId == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
    }
}