package com.techmove.fixnow.review.application.services.queryServices;

import com.techmove.fixnow.review.domain.model.queries.GetAllReviewsQuery;
import com.techmove.fixnow.review.domain.model.queries.GetReviewsByWorkerIdQuery;
import com.techmove.fixnow.review.domain.services.ReviewQueryService;
import com.techmove.fixnow.review.infrastructure.persistence.jpa.repositories.ReviewRepository;
import com.techmove.fixnow.review.interfaces.rest.resources.ReviewResource;
import com.techmove.fixnow.review.interfaces.rest.transform.ReviewResourceFromEntityAssembler;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {
    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewResource> handle(GetAllReviewsQuery query) {
        return reviewRepository.findAll().stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
    }

    @Override
    public List<ReviewResource> handle(GetReviewsByWorkerIdQuery query) {
        return reviewRepository.findByWorkerId(query.getWorkerId()).stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
    }
}