package com.techmove.fixnow.alerts.interfaces.rest.transform;

import com.techmove.fixnow.alerts.domain.model.commands.CreateAlertCommand;
import com.techmove.fixnow.alerts.interfaces.rest.resources.CreateAlertResource;

import java.time.LocalDateTime;

public class CreateAlertCommandFromResourceAssembler {
    public static CreateAlertCommand toCommandFromResource(CreateAlertResource resource) {
        return new CreateAlertCommand(
                resource.userId(),
                resource.type(),
                resource.logoUrl(),
                resource.message(),
                resource.read(),
                resource.link(),
                resource.senderUserId(),
                resource.senderFirstName(),
                resource.senderLastName(),
                resource.senderProfilePicture()
        );
    }
}
