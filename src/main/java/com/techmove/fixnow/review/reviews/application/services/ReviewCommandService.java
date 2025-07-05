package com.techmove.fixnow.review.reviews.application.services;

import com.techmove.fixnow.review.reviews.domain.Review;
import com.techmove.fixnow.review.reviews.interfaces.rest.*;
import com.techmove.fixnow.review.infrastructure.persistence.jpa.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommandService {
    private final ReviewRepository reviewRepository;

    public ReviewCommandService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ReviewDTO createReview(CreateReviewCommand command) {
        Review saved = reviewRepository.save(ReviewAssembler.toEntity(command));
        return ReviewAssembler.toDTO(saved);
    }
}
