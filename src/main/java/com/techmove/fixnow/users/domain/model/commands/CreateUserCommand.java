package com.techmove.fixnow.users.domain.model.commands;

public record CreateUserCommand(
        Long accountId,
        String firstName,
        String lastName,
        String description
) {

    public CreateUserCommand {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null or blank");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
    }
}