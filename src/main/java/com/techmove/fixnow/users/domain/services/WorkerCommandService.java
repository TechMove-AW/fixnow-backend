package com.techmove.fixnow.users.domain.services;

import com.techmove.fixnow.users.domain.model.commands.AddWorkerServiceCommand;
import com.techmove.fixnow.users.domain.model.commands.CreateWorkerCommand;
import com.techmove.fixnow.users.domain.model.commands.RemoveWorkerServiceCommand;
import com.techmove.fixnow.users.domain.model.entities.Worker;

import java.util.Optional;

public interface WorkerCommandService {
    Optional<Worker> handle(CreateWorkerCommand command);
    Optional<Worker> handle(AddWorkerServiceCommand command);
    Optional<Worker> handle(RemoveWorkerServiceCommand command);
}