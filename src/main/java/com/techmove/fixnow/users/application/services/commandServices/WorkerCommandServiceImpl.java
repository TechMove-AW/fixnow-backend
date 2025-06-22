package com.techmove.fixnow.users.application.services.commandServices;

import com.techmove.fixnow.users.domain.model.commands.AddWorkerServiceCommand;
import com.techmove.fixnow.users.domain.model.commands.CreateWorkerCommand;
import com.techmove.fixnow.users.domain.model.commands.RemoveWorkerServiceCommand;
import com.techmove.fixnow.users.domain.model.entities.Worker;
import com.techmove.fixnow.users.domain.services.WorkerCommandService;
import com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories.UserRepository;
import com.techmove.fixnow.users.infrastructure.persitence.jpa.repositories.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WorkerCommandServiceImpl implements WorkerCommandService {
    
    private final WorkerRepository workerRepository;
    private final UserRepository userRepository;

    public WorkerCommandServiceImpl(WorkerRepository workerRepository, UserRepository userRepository) {
        this.workerRepository = workerRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Worker> handle(CreateWorkerCommand command) {
        var user = userRepository.findByAccountId(command.userId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User with ID " + command.userId() + " does not exist");
        }
        
        if (workerRepository.existsByUser(user.get())) {
            throw new IllegalStateException("User with ID " + command.userId() + " already has a worker profile");
        }
        
        var worker = new Worker(
                user.get(),
                command.workerCategoryId(),
                command.availability(),
                command.hourlyRate()
        );
        
        workerRepository.save(worker);
        return Optional.of(worker);
    }

    @Override
    public Optional<Worker> handle(AddWorkerServiceCommand command) {
        var worker = workerRepository.findByWorkerId(command.workerId());
        if (worker.isEmpty()) {
            return Optional.empty();
        }
        
        var existingWorker = worker.get();
        existingWorker.addService(command.service());
        workerRepository.save(existingWorker);
        return Optional.of(existingWorker);
    }

    @Override
    public Optional<Worker> handle(RemoveWorkerServiceCommand command) {
        var worker = workerRepository.findByWorkerId(command.workerId());
        if (worker.isEmpty()) {
            return Optional.empty();
        }
        
        var existingWorker = worker.get();
        existingWorker.removeService(command.service());
        workerRepository.save(existingWorker);
        return Optional.of(existingWorker);
    }
}