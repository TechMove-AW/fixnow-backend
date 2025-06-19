package com.techmove.fixnow.users.domain.model.commands;

public record CreateWorkerCategoryCommand(
        String displayName,
        String slug
) {

    public CreateWorkerCategoryCommand {
        if (displayName == null || displayName.isBlank()) {
            throw new IllegalArgumentException("displayName cannot be null or blank");
        }
        if (slug == null || slug.isBlank()) {
            throw new IllegalArgumentException("slug cannot be null or blank");
        }
    }
}