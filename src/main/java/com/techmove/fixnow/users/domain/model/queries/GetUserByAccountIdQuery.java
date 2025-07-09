package com.techmove.fixnow.users.domain.model.queries;

public record GetUserByAccountIdQuery(Long accountId) {
    public GetUserByAccountIdQuery {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
    }
}