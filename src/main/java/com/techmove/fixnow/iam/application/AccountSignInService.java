package com.techmove.fixnow.iam.application;

import com.techmove.fixnow.iam.domain.model.aggregates.Account;
import com.techmove.fixnow.iam.domain.model.commands.SignInCommand;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountSignInService {
    private final AccountQueryService accountQueryService;
    private final PasswordEncoder passwordEncoder;

    public AccountSignInService(AccountQueryService accountQueryService, PasswordEncoder passwordEncoder) {
        this.accountQueryService = accountQueryService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Authenticates a user with email and password
     * @param command the sign in command
     * @return the authenticated account
     * @throws IllegalArgumentException if credentials are invalid
     */
    public Account signIn(SignInCommand command) {
        var accountOpt = accountQueryService.findByEmail(command.email());
        if (accountOpt.isEmpty()) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }
        Account account = accountOpt.get();
        if (!passwordEncoder.matches(command.password(), account.getPassword())) {
            throw new IllegalArgumentException("Credenciales inválidas");
        }
        return account;
    }
}