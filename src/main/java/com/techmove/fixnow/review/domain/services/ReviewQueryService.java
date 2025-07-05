package com.techmove.fixnow.review.domain.services;

import com.techmove.fixnow.review.domain.model.queries.GetAllReviewsQuery;
import com.techmove.fixnow.review.domain.model.queries.GetReviewsByWorkerIdQuery;
import com.techmove.fixnow.review.interfaces.rest.resources.ReviewResource;

import java.util.List;

public interface ReviewQueryService {
    List<ReviewResource> handle(GetAllReviewsQuery query);
    List<ReviewResource> handle(GetReviewsByWorkerIdQuery query);
}