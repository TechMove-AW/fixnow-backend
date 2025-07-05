package com.techmove.fixnow.review.domain.model.commands;

public class CreateReviewCommand {
    private Long workerId;
    private Long userId;
    private Double rating;
    private String comment;

    public CreateReviewCommand() {}

    public CreateReviewCommand(Long workerId, Long userId, Double rating, String comment) {
        this.workerId = workerId;
        this.userId = userId;
        this.rating = rating;
        this.comment = comment;
    }

    public Long getWorkerId() { return workerId; }
    public void setWorkerId(Long workerId) { this.workerId = workerId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}