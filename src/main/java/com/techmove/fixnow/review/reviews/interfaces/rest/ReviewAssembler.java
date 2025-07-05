package com.techmove.fixnow.review.reviews.interfaces.rest;
import com.techmove.fixnow.review.reviews.domain.Review;

import java.time.LocalDateTime;

public class ReviewAssembler {
    public static Review toEntity(CreateReviewCommand command) {
        Review review = new Review();
        review.setWorkerId(command.getWorkerId());
        review.setUserId(command.getUserId());
        review.setRating(command.getRating());
        review.setComment(command.getComment());
        review.setDate(LocalDateTime.now());
        return review;
    }

    public static ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setWorkerId(review.getWorkerId());
        dto.setUserId(review.getUserId());
        dto.setRating(review.getRating());
        dto.setComment(review.getComment());
        dto.setDate(review.getDate());
        return dto;
    }
}
