package com.techmove.fixnow.alerts.application.services.commandservices;

import com.techmove.fixnow.alerts.domain.model.aggregates.Alert;
import com.techmove.fixnow.alerts.domain.model.commands.CreateAlertCommand;
import com.techmove.fixnow.alerts.domain.model.valueobjects.Sender;
import com.techmove.fixnow.alerts.infraestructure.persistence.jpa.repositories.AlertRepository;
import org.springframework.stereotype.Service;

@Service
public class AlertCommandService {

    private final AlertRepository alertRepository;

    public AlertCommandService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public Alert handle(CreateAlertCommand command) {
        Sender sender = new Sender(command.senderUserId(), command.senderFirstName(), command.senderLastName(), command.senderProfilePicture());
        Alert alert = new Alert(
                command.userId(),
                command.type(),
                command.logoUrl(),
                command.message(),
                command.read(),
                command.link(),
                sender
        );
        return alertRepository.save(alert);
    }

    public void markAlertAsRead(Long alertId) {
        alertRepository.findById(alertId).ifPresent(alert -> {
            alert.markAsRead();
            alertRepository.save(alert);
        });
    }
}
