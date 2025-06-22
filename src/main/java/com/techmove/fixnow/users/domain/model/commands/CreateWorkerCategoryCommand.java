package com.techmove.fixnow.users.domain.model.commands;

public record CreateWorkerCategoryCommand(
        String displayName
) {

    public CreateWorkerCategoryCommand {
        if (displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("displayName cannot be null or blank");
        }
    }
}