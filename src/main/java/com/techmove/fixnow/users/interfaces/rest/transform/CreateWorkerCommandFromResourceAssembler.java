package com.techmove.fixnow.users.interfaces.rest.transform;

import com.techmove.fixnow.users.domain.model.commands.CreateWorkerCommand;
import com.techmove.fixnow.users.domain.model.valueobjects.Money;
import com.techmove.fixnow.users.interfaces.rest.resources.CreateWorkerResource;

public class CreateWorkerCommandFromResourceAssembler {
    public static CreateWorkerCommand toCommandFromResource(CreateWorkerResource resource) {
        return new CreateWorkerCommand(
                resource.userId(),
                resource.workerCategoryId(),
                resource.availability(),
                new Money(resource.hourlyRateAmount())
        );
    }
}