package com.techmove.fixnow.alerts.interfaces.rest.resources;

public record SenderResource(
        Long userId,
        String firstName,
        String lastName,
        String profilePicture
) {}