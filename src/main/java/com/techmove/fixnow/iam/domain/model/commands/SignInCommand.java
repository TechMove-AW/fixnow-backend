package com.techmove.fixnow.iam.domain.model.commands;

public record SignInCommand(String email, String password) {
    public SignInCommand {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }
        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
    }
}