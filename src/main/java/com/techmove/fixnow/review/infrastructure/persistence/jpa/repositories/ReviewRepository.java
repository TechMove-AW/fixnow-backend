package com.techmove.fixnow.review.infrastructure.persistence.jpa.repositories;

import com.techmove.fixnow.review.domain.model.aggregates.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByWorkerId(Long workerId);
}
