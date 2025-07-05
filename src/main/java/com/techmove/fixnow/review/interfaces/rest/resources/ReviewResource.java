package com.techmove.fixnow.review.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ReviewResource(
    Long id,
    Long workerId,
    Long userId,
    Double rating,
    String comment,
    LocalDateTime date
) {
}