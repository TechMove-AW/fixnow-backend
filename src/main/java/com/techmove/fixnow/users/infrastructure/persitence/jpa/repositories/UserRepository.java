package com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories;

import com.techmove.fixnow.users.domain.model.aggregates.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByAccountId(String accountId);
}