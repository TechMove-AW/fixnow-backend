package com.techmove.fixnow.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Money(float amount) {

    public Money{
        if(amount < 0){
            throw new IllegalArgumentException("Cantidad no valida");
        }
    }
}
