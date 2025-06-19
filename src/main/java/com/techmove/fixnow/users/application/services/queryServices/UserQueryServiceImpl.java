package com.techmove.fixnow.users.application.services.queryServices;

import com.techmove.fixnow.users.domain.model.aggregates.User;
import com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories.UserRepository;

import java.util.Optional;

public class UserQueryServiceImpl
{
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
