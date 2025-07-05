package com.techmove.fixnow.review.application.services.commandServices;

import com.techmove.fixnow.review.domain.model.aggregates.Review;
import com.techmove.fixnow.review.domain.model.commands.CreateReviewCommand;
import com.techmove.fixnow.review.domain.services.ReviewCommandService;
import com.techmove.fixnow.review.infrastructure.persistence.jpa.repositories.ReviewRepository;
import com.techmove.fixnow.review.interfaces.rest.resources.ReviewResource;
import com.techmove.fixnow.review.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public ReviewResource createReview(CreateReviewCommand command) {
        Review review = new Review();
        review.setWorkerId(command.getWorkerId());
        review.setUserId(command.getUserId());
        review.setRating(command.getRating());
        review.setComment(command.getComment());
        review.setDate(java.time.LocalDateTime.now());
        
        Review saved = reviewRepository.save(review);
        return ReviewResourceFromEntityAssembler.toResourceFromEntity(saved);
    }
}