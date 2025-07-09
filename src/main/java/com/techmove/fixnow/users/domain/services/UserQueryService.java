package com.techmove.fixnow.users.domain.services;

import com.techmove.fixnow.users.domain.model.aggregates.User;
import com.techmove.fixnow.users.domain.model.queries.GetUserByIdQuery;
import com.techmove.fixnow.users.domain.model.queries.GetUserByAccountIdQuery;

import java.util.Optional;

public interface UserQueryService {
    Optional<User> handle(GetUserByIdQuery query);
    Optional<User> handle(GetUserByAccountIdQuery query);
}
