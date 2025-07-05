package com.techmove.fixnow.review.interfaces.rest.resources;

public record CreateReviewResource(
    Long workerId,
    Long userId,
    Double rating,
    String comment
) {
    public CreateReviewResource {
        if (workerId == null || workerId <= 0) {
            throw new IllegalArgumentException("Worker ID cannot be null or negative");
        }
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("User ID cannot be null or negative");
        }
        if (rating == null || rating < 0 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        }
    }
}