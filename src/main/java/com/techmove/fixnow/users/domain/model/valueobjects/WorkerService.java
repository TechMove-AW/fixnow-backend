package com.techmove.fixnow.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record WorkerService(
        String serviceName,
        Float price,
        String description,
        String imageUrl
) {
    public WorkerService {
        if (serviceName == null || serviceName.isBlank()) {
            throw new IllegalArgumentException("El nombre del servicio no puede estar vacío");
        }
        if (price == null || price <= 0) {
            throw new IllegalArgumentException("El precio debe ser mayor que cero");
        }
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar vacía");
        }
        if (imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("La URL de la imagen no puede estar vacía");
        }
    }

    public WorkerService updateServiceInfo(String serviceName, Float price, String description, String imageUrl) {
        return new WorkerService(serviceName, price, description, imageUrl);
    }
}
