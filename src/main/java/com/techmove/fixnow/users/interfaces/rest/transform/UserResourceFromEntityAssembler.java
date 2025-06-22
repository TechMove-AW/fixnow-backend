package com.techmove.fixnow.users.interfaces.rest.transform;


import com.techmove.fixnow.users.domain.model.aggregates.User;
import com.techmove.fixnow.users.interfaces.rest.resources.UserResource;


public class UserResourceFromEntityAssembler {

    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId().toString(),
                entity.getAccountId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getDescription()
        );
    }
}
