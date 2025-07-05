package com.techmove.fixnow.review.domain.model.queries;

public class GetReviewsByWorkerIdQuery {
    private Long workerId;

    public GetReviewsByWorkerIdQuery() {}

    public GetReviewsByWorkerIdQuery(Long workerId) {
        this.workerId = workerId;
    }

    public Long getWorkerId() { return workerId; }
    public void setWorkerId(Long workerId) { this.workerId = workerId; }
}