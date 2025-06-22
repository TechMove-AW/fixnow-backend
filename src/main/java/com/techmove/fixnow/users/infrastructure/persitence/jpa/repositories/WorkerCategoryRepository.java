package com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories;

import com.techmove.fixnow.users.domain.model.aggregates.WorkerCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkerCategoryRepository extends JpaRepository<WorkerCategory, String> {
    Optional<WorkerCategory> findBySlug(String slug);
    boolean existsBySlug(String slug);
}
