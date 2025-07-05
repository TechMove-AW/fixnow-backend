package com.techmove.fixnow.alerts.domain.model.aggregates;

import com.techmove.fixnow.alerts.domain.model.valueobjects.Sender;
import com.techmove.fixnow.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "alerts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Alert extends AuditableAbstractAggregateRoot<Alert> {

    @Column(nullable = false)
    private Long userId;
    
    @Column(nullable = false)
    private String type;
    
    @Column
    private String logoUrl;
    
    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;
    
    @Column(nullable = false)
    private boolean read;
    
    @Column
    private String link;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "userId", column = @Column(name = "sender_user_id"))
    })
    private Sender sender;

    public void markAsRead() {
        this.read = true;
    }
}
