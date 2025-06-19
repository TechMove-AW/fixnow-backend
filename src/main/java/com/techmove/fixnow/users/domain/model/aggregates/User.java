package com.techmove.fixnow.users.domain.model.aggregates;

import com.techmove.fixnow.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.techmove.fixnow.users.domain.model.commands.baseuser.CreateUserCommand;
import com.techmove.fixnow.users.domain.model.valueobjects.AccountId;
import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "users")
@Getter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @Embedded
    private AccountId accountId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private String role; // "CUSTOMER" or "WORKER"

    protected User() {
    }

    public User(CreateUserCommand command) {
        this();
        this.accountId = command.accountId();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.description = command.description();
        this.role = command.role();
    }

    public void updateUserInfo(String firstName, String lastName, String description) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}