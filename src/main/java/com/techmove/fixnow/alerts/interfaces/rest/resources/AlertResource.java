package com.techmove.fixnow.alerts.interfaces.rest.resources;

public record AlertResource(
        Long id,
        Long userId,
        String type,
        String logoUrl,
        String message,
        boolean read,
        String link,
        SenderResource sender
) {}
