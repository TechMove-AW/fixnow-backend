package com.techmove.fixnow.alerts.domain.model.commands;

public record CreateAlertCommand(
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