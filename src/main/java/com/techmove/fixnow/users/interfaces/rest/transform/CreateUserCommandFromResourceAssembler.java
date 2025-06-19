package com.techmove.fixnow.users.interfaces.rest.transform;

import com.techmove.fixnow.users.domain.model.commands.baseuser.CreateUserCommand;
import com.techmove.fixnow.users.domain.model.valueobjects.AccountId;
import com.techmove.fixnow.users.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {

    public static CreateUserCommand toCommandFromResource(CreateUserResource resource) {
        return new CreateUserCommand(
                new AccountId(resource.accountId()),
                resource.firstName(),
                resource.lastName(),
                resource.description(),
                resource.role()
        );
    }
}
