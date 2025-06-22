package com.techmove.fixnow.users.interfaces.rest.transform;

import com.techmove.fixnow.users.domain.model.commands.CreateUserCommand;
import com.techmove.fixnow.users.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {

    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                resource.accountId().toString(),
                resource.firstName(),
                resource.lastName(),
                resource.description()
        );
    }
}
