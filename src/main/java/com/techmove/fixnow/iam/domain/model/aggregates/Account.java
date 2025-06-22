package com.techmove.fixnow.iam.domain.model.aggregates;

import com.techmove.fixnow.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

/**
 * Account aggregate root
 * This class represents the aggregate root for the Account entity in the IAM bounded context.
 * It contains the essential authentication information: email, password, and role.
 */
@Getter
@Entity
@Table(name = "accounts")
public class Account extends AuditableAbstractAggregateRoot<Account> {

    @NotBlank
    @Email
    @Size(max = 100)
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank
    @Size(max = 120)
    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    protected Account() {
        super();
    }

    public Account(String email, String password, Role role) {
        super();
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public enum Role {
        CUSTOMER, WORKER
    }
}