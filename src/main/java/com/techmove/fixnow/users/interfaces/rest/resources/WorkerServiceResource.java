package com.techmove.fixnow.users.interfaces.rest.resources;

public record WorkerServiceResource(
        String serviceName,
        Float price,
        String description,
        String imageUrl
) {
}