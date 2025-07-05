package com.techmove.fixnow.users.domain.model.entities;

import com.techmove.fixnow.shared.domain.model.entities.AuditableModel;
import com.techmove.fixnow.users.domain.model.aggregates.User;
import com.techmove.fixnow.users.domain.model.valueobjects.WorkerService;
import com.techmove.fixnow.users.domain.model.valueobjects.Money;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * Worker entity
 * @summary
 * This entity represents a worker in the FixNow platform.
 */
@Getter
@Entity
public class Worker extends AuditableModel {


    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long workerCategoryId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "worker_services")
    private List<WorkerService> workerServices = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "worker_skills")
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    @Column(nullable = false)
    private String availability;

    @Embedded
    private Money hourlyRate;

    @Column(nullable = false)
    private Integer projectsCompleted = 0;


    protected Worker() {
    }

    public Worker(User user, Long workerCategoryId, String availability, Money hourlyRate) {
        this();
        this.user = user;
        this.workerCategoryId = workerCategoryId;
        this.availability = availability;
        this.hourlyRate = hourlyRate;
    }

    public void addService(WorkerService service) {
        if (service != null && !this.workerServices.contains(service)) {
            this.workerServices.add(service);
        }
    }

    public void removeService(WorkerService service) {
        this.workerServices.remove(service);
    }

    public void addSkill(String skill) {
        if (skill != null && !skill.trim().isEmpty() && !this.skills.contains(skill)) {
            this.skills.add(skill.trim());
        }
    }

    public void removeSkill(String skill) {
        this.skills.remove(skill);
    }

    public void updateWorkerInfo(Long workerCategoryId, String availability, Money hourlyRate) {
        this.workerCategoryId = workerCategoryId;
        this.availability = availability;
        this.hourlyRate = hourlyRate;
    }

    public void completeProject() {
        this.projectsCompleted++;
    }
}