package com.techmove.fixnow.iam.infrastructure.persistence.jpa;

import com.techmove.fixnow.iam.domain.model.aggregates.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Account repository
 * <p>
 *     This repository handles account persistence operations.
 * </p>
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * Finds an account by email
     * @param email the email to search for
     * @return the account if found
     */
    Optional<Account> findByEmail(String email);
    
    /**
     * Checks if an account exists by email
     * @param email the email to check
     * @return true if exists, false otherwise
     */
    boolean existsByEmail(String email);
}