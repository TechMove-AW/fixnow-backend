package com.techmove.fixnow.iam.application;

import com.techmove.fixnow.iam.domain.model.aggregates.Account;
import com.techmove.fixnow.iam.domain.model.commands.SignUpCommand;
import com.techmove.fixnow.iam.infrastructure.persistence.jpa.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountCommandService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountCommandService(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new account
     * @param command the sign up command
     * @return the created account
     * @throws IllegalArgumentException if the email is already registered
     */
    public Account registerAccount(SignUpCommand command) {
        if (accountRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        String hashedPassword = passwordEncoder.encode(command.password());
        Account account = new Account(command.email(), hashedPassword, command.role());
        return accountRepository.save(account);
    }
}