package com.techmove.fixnow.users.application.services.commandServices;

import com.techmove.fixnow.users.domain.model.aggregates.User;
import com.techmove.fixnow.users.domain.model.commands.CreateUserCommand;
import com.techmove.fixnow.users.domain.services.UserCommandService;
import com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService
{
    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        var userWithAccountIdExists = userRepository.findByAccountId(command.accountId());
        if (userWithAccountIdExists.isPresent()) {
            throw new IllegalArgumentException("User with accountId " + command.accountId() + " exists!");
        }
        var userCreated = new User(command);
        return Optional.of(this.userRepository.save(userCreated));
    }
}
