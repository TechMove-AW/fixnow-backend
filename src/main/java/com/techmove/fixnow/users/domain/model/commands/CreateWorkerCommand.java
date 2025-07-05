package com.techmove.fixnow.users.domain.model.commands;

import com.techmove.fixnow.users.domain.model.valueobjects.Money;

/**
 * Command to create a new worker
 */
public record CreateWorkerCommand(
        Long userId,
        Long workerCategoryId,
        String availability,
        Money hourlyRate
) {
    public CreateWorkerCommand {
        if (userId == null ) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        if (workerCategoryId == null) {
            throw new IllegalArgumentException("Worker category ID cannot be null or empty");
        }
        if (availability == null || availability.trim().isEmpty()) {
            throw new IllegalArgumentException("Availability cannot be null or empty");
        }
        if (hourlyRate == null) {
            throw new IllegalArgumentException("Hourly rate cannot be null");
        }
    }
}