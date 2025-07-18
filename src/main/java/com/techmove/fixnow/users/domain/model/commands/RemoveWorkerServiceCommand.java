package com.techmove.fixnow.users.domain.model.commands;

import com.techmove.fixnow.users.domain.model.valueobjects.WorkerService;

public record RemoveWorkerServiceCommand(Long workerId, WorkerService service) {
    public RemoveWorkerServiceCommand {
        if (workerId == null) {
            throw new IllegalArgumentException("Worker ID cannot be null");
        }
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
    }
}