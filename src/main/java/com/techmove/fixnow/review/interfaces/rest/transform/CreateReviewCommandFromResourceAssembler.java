package com.techmove.fixnow.review.interfaces.rest.transform;

import com.techmove.fixnow.review.domain.model.commands.CreateReviewCommand;
import com.techmove.fixnow.review.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {
    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(
            resource.workerId(),
            resource.userId(),
            resource.rating(),
            resource.comment()
        );
    }
}