package com.techmove.fixnow.iam.domain.model.commands;

import com.techmove.fixnow.iam.domain.model.aggregates.Account;

public record SignUpCommand(String email, String password, Account.Role role) {
    public SignUpCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (role == null) {
            throw new IllegalArgumentException("Role cannot be null");
        }
    }
}