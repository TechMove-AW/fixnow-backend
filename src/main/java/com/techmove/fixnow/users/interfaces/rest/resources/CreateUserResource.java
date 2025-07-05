package com.techmove.fixnow.users.interfaces.rest.resources;

public record CreateUserResource(
        Long accountId,
        String firstName,
        String lastName,
        String description
    ) {
    /**
     * Validates the resource.
     *
     * @throws IllegalArgumentException if the resource is invalid.
     */
    public CreateUserResource {
        if (firstName == null || firstName.isBlank()) throw new IllegalArgumentException("First name is required");
        if (lastName == null || lastName.isBlank()) throw new IllegalArgumentException("Last name is required");
    }
}
