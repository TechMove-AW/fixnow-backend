package com.techmove.fixnow.users.domain.model.aggregates;

import com.techmove.fixnow.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.techmove.fixnow.users.domain.model.commands.CreateUserCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Table(name = "users")
@Getter
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {
    @Column(nullable = false, unique = true)
    private String accountId;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(columnDefinition = "TEXT")
    private String description;

    protected User() {
    }

    public User(CreateUserCommand command) {
        this();
        this.accountId = command.accountId();
        this.firstName = command.firstName();
        this.lastName = command.lastName();
        this.description = command.description();
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