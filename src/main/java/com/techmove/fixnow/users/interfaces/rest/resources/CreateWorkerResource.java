package com.techmove.fixnow.users.interfaces.rest.resources;

import java.util.UUID;

public record CreateWorkerResource(
        UUID userId,
        String workerCategoryId,
        String availability,
        Float hourlyRateAmount
) {
    /**
     * Validates the resource.
     *
     * @throws IllegalArgumentException if the resource is invalid.
     */
    public CreateWorkerResource {
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        if (workerCategoryId == null || workerCategoryId.isBlank()) {
            throw new IllegalArgumentException("Worker category ID is required");
        }
        if (availability == null || availability.isBlank()) {
            throw new IllegalArgumentException("Availability is required");
        }
        if (hourlyRateAmount == null || hourlyRateAmount <= 0) {
            throw new IllegalArgumentException("Hourly rate amount must be greater than 0");
        }
    }
}