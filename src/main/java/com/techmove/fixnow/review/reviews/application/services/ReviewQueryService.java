package com.techmove.fixnow.review.reviews.application.services;

import com.techmove.fixnow.review.infrastructure.persistence.jpa.repositories.ReviewRepository;
import com.techmove.fixnow.review.reviews.interfaces.rest.ReviewDTO;
import com.techmove.fixnow.review.reviews.interfaces.rest.ReviewAssembler;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public ReviewQueryService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(ReviewAssembler::toDTO)
                .toList();
    }

    public List<ReviewDTO> getReviewsByWorkerId(Long workerId) {
        return reviewRepository.findByWorkerId(workerId).stream()
                .map(ReviewAssembler::toDTO)
                .toList();
    }
}
