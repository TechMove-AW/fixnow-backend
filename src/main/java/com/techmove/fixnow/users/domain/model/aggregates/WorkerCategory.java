package com.techmove.fixnow.users.domain.model.aggregates;

import com.techmove.fixnow.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.techmove.fixnow.users.domain.model.commands.CreateWorkerCategoryCommand;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class WorkerCategory extends AuditableAbstractAggregateRoot<WorkerCategory> {
    @Column(nullable = false)
    private String displayName;

    @Column(unique=true, nullable = false)
    private String slug;

    protected WorkerCategory() {
    }

    public WorkerCategory(CreateWorkerCategoryCommand command) {
        this();
        this.displayName = command.displayName();
        this.slug = slugify(command.displayName());
    }

    public void updateWorkerCategory(String displayName) {
        this.displayName = displayName;
        this.slug = slugify(displayName);
    }

    private String slugify(String slug) {
        if (slug == null) return "";
        String normalized = java.text.Normalizer.normalize(slug, java.text.Normalizer.Form.NFD);

        return normalized
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "")
                .replaceAll("[^a-zA-Z0-9\\s]", "")
                .trim()
                .replaceAll("\\s+", "-")
                .toLowerCase();
    }
}