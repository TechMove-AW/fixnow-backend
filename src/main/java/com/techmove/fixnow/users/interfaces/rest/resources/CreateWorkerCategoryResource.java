package com.techmove.fixnow.users.interfaces.rest.resources;

public record CreateWorkerCategoryResource(
        String displayName
) {
    public CreateWorkerCategoryResource{
        if(displayName == null || displayName.isBlank()){
            throw new IllegalArgumentException("displayName is required!");
        }
    }
}
