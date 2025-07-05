package com.techmove.fixnow.review.interfaces.rest.transform;

import com.techmove.fixnow.review.domain.model.aggregates.Review;
import com.techmove.fixnow.review.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {
    public static ReviewResource toResourceFromEntity(Review entity) {
        return new ReviewResource(
            entity.getId(),
            entity.getWorkerId(),
            entity.getUserId(),
            entity.getRating(),
            entity.getComment(),
            entity.getDate()
        );
    }
}