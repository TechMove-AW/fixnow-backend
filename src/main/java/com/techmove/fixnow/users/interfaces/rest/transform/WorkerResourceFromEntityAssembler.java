package com.techmove.fixnow.users.interfaces.rest.transform;

import com.techmove.fixnow.users.domain.model.entities.Worker;
import com.techmove.fixnow.users.interfaces.rest.resources.WorkerResource;
import com.techmove.fixnow.users.interfaces.rest.resources.WorkerServiceResource;

import java.util.stream.Collectors;

public class WorkerResourceFromEntityAssembler {
    public static WorkerResource toResourceFromEntity(Worker entity) {
        var services = entity.getWorkerServices().stream()
                .map(service -> new WorkerServiceResource(
                        service.serviceName(),
                        service.price(),
                        service.description(),
                        service.imageUrl()
                ))
                .collect(Collectors.toList());

        return new WorkerResource(
                entity.getWorkerId().toString(),
                entity.getUser().getAccountId(),
                entity.getWorkerCategoryId(),
                entity.getAvailability(),
                entity.getHourlyRate().amount(),
                entity.getProjectsCompleted(),
                entity.getSkills(),
                services
        );
    }
}