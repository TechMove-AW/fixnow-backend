package com.techmove.fixnow.users.interfaces.rest.resources;

public record UserResource(
        String id,
        String accountId,
        String firstName,
        String lastName,
        String description
        ) {
}
