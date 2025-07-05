package com.techmove.fixnow.alerts.interfaces.rest.resources;

public record CreateAlertResource(
        Long userId,
        String type,
        String logoUrl,
        String message,
        boolean read,
        String link,
        Long senderUserId,
        String senderFirstName,
        String senderLastName,
        String senderProfilePicture
) {}
