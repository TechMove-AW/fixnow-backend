package com.techmove.fixnow.users.domain.services;

import com.techmove.fixnow.users.domain.model.aggregates.User;
import com.techmove.fixnow.users.domain.model.commands.baseuser.CreateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    public Optional<User> handle(CreateUserCommand command);
}
