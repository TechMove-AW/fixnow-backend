package com.techmove.fixnow.review.domain.services;

import com.techmove.fixnow.review.domain.model.commands.CreateReviewCommand;
import com.techmove.fixnow.review.interfaces.rest.resources.ReviewResource;

public interface ReviewCommandService {
    ReviewResource createReview(CreateReviewCommand command);
}