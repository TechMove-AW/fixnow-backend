package com.techmove.fixnow.users.interfaces.rest.resources;

public record UserResource(
        Long id,
        Long accountId,
        Long workerId,
        String firstName,
        String lastName,
        String description,
        String avatarUrl
        ) {
}
