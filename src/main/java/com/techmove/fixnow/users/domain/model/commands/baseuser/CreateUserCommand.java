package com.techmove.fixnow.users.domain.model.commands.baseuser;

import com.techmove.fixnow.users.domain.model.valueobjects.AccountId;


public record CreateUserCommand(
        AccountId accountId,
        String firstName,
        String lastName,
        String description,
        String role
) {

    public CreateUserCommand {
        if (accountId == null) {
            throw new IllegalArgumentException("Account ID cannot be null");
        }
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }
        if (role == null || (!role.equals("CUSTOMER") && !role.equals("WORKER"))) {
            throw new IllegalArgumentException("Role must be either CUSTOMER or WORKER");
        }
    }
}