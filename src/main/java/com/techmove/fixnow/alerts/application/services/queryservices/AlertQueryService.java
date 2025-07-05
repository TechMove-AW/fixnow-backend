package com.techmove.fixnow.alerts.application.services.queryservices;

import com.techmove.fixnow.alerts.domain.model.aggregates.Alert;
import com.techmove.fixnow.alerts.domain.model.queries.GetAlertsByUserIdQuery;
import com.techmove.fixnow.alerts.infraestructure.persistence.jpa.repositories.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertQueryService {

    private final AlertRepository alertRepository;

    public AlertQueryService(AlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    public List<Alert> handle(GetAlertsByUserIdQuery query) {
        return alertRepository.findByUserId(query.userId());
    }

    public List<Alert> getUnreadAlertsByUserId(Long userId) {
        return alertRepository.findByUserIdAndReadFalse(userId);
    }

    public Optional<Alert> getAlertById(Long alertId) {
        return alertRepository.findById(alertId);
    }
}
