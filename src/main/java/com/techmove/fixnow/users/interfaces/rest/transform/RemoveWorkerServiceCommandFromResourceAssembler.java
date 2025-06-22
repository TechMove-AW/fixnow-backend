package com.techmove.fixnow.users.interfaces.rest.transform;

import com.techmove.fixnow.users.domain.model.valueobjects.Money;
import com.techmove.fixnow.users.domain.model.commands.RemoveWorkerServiceCommand;
import com.techmove.fixnow.users.domain.model.valueobjects.WorkerService;
import com.techmove.fixnow.users.interfaces.rest.resources.AddWorkerServiceResource;

import java.util.UUID;

public class RemoveWorkerServiceCommandFromResourceAssembler {
    public static RemoveWorkerServiceCommand toCommandFromResource(UUID workerId, AddWorkerServiceResource resource) {
        var money = new Money(resource.price());
        var workerService = new WorkerService(
                resource.serviceName(),
                money.amount(),
                resource.description(),
                resource.imageUrl()
        );
        return new RemoveWorkerServiceCommand(workerId, workerService);
    }
}