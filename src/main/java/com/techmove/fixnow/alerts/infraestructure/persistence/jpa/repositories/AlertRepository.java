package com.techmove.fixnow.alerts.infraestructure.persistence.jpa.repositories;

import com.techmove.fixnow.alerts.domain.model.aggregates.Alert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findByUserId(Long userId);
    List<Alert> findByUserIdAndReadFalse(Long userId);
}
