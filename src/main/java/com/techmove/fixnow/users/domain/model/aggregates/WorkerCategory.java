package com.techmove.fixnow.users.domain.model.aggregates;

import com.techmove.fixnow.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.techmove.fixnow.users.domain.model.commands.CreateWorkerCategoryCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class WorkerCategory extends AuditableAbstractAggregateRoot<User> {
    @Column(nullable = false)
    private String displayName;

    @Column(unique=true, nullable = false)
    private String slug;

    protected WorkerCategory() {
    }

    public WorkerCategory(CreateWorkerCategoryCommand command) {
        this();
        this.displayName = command.displayName();
        this.slug = command.slug();
    }

    public void updateWorkerCategory(String displayName, String slug) {
        this.displayName = displayName;
        this.slug = slug;
    }
}