package com.techmove.fixnow.iam.application;

import com.techmove.fixnow.iam.domain.model.aggregates.Account;
import com.techmove.fixnow.iam.infrastructure.persistence.jpa.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AccountQueryService {
    private final AccountRepository accountRepository;

    public AccountQueryService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Finds an account by email
     * @param email the email to search for
     * @return the account if found
     */
    public Optional<Account> findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    /**
     * Finds an account by ID
     * @param id the account ID
     * @return the account if found
     */
    public Optional<Account> findById(String id) {
        return accountRepository.findById(id);
    }
}