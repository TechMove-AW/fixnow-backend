package com.techmove.fixnow.users.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record AddWorkerServiceResource(
        @NotBlank(message = "Service name cannot be null or empty")
        String serviceName,
        
        @NotNull(message = "Price cannot be null")
        @Positive(message = "Price must be greater than zero")
        Float price,
        
        @NotBlank(message = "Description cannot be null or empty")
        String description,
        
        String imageUrl
) {
}