package com.techmove.fixnow.review.reviews.interfaces.rest;


import java.time.LocalDateTime;

public class ReviewDTO {
    private Long workerId;
    private Long userId;
    private Double rating;
    private String comment;
    private LocalDateTime date;

    public Long getWorkerId() { return workerId; }
    public void setWorkerId(Long workerId) { this.workerId = workerId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

}
