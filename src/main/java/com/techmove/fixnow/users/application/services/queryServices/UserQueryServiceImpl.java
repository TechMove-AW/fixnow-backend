package com.techmove.fixnow.users.application.services.queryServices;

import com.techmove.fixnow.users.domain.model.aggregates.User;
import com.techmove.fixnow.users.domain.model.queries.GetUserByIdQuery;
import com.techmove.fixnow.users.domain.services.UserQueryService;
import com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService
{
    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }
}
