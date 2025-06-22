package com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories;

import com.techmove.fixnow.users.domain.model.aggregates.User;
import com.techmove.fixnow.users.domain.model.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    Optional<Worker> findByWorkerId(UUID workerId);
    Optional<Worker> findByUser(User user);
    boolean existsByUser(User user);
}