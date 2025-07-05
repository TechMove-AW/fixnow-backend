package com.techmove.fixnow.alerts.interfaces.rest.transform;

import com.techmove.fixnow.alerts.domain.model.aggregates.Alert;
import com.techmove.fixnow.alerts.interfaces.rest.resources.AlertResource;
import com.techmove.fixnow.alerts.interfaces.rest.resources.SenderResource;

public class AlertResourceFromEntityAssembler {
    public static AlertResource toResourceFromEntity(Alert entity) {
        SenderResource senderResource = new SenderResource(
                entity.getSender().getUserId(),
                entity.getSender().getFirstName(),
                entity.getSender().getLastName(),
                entity.getSender().getProfilePicture()
        );
        return new AlertResource(
                entity.getId(),
                entity.getUserId(),
                entity.getType(),
                entity.getLogoUrl(),
                entity.getMessage(),
                entity.isRead(),
                entity.getLink(),
                senderResource
        );
    }
}
