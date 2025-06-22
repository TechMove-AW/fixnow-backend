package com.techmove.fixnow.users.interfaces.rest;

import com.techmove.fixnow.users.domain.model.queries.GetWorkerByIdQuery;
import com.techmove.fixnow.users.domain.services.WorkerCommandService;
import com.techmove.fixnow.users.domain.services.WorkerQueryService;
import com.techmove.fixnow.users.interfaces.rest.resources.AddWorkerServiceResource;
import com.techmove.fixnow.users.interfaces.rest.resources.CreateWorkerResource;
import com.techmove.fixnow.users.interfaces.rest.resources.WorkerResource;
import com.techmove.fixnow.users.interfaces.rest.transform.AddWorkerServiceCommandFromResourceAssembler;
import com.techmove.fixnow.users.interfaces.rest.transform.CreateWorkerCommandFromResourceAssembler;
import com.techmove.fixnow.users.interfaces.rest.transform.RemoveWorkerServiceCommandFromResourceAssembler;
import com.techmove.fixnow.users.interfaces.rest.transform.WorkerResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/workers", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Workers", description = "Available Workers Endpoints")
public class WorkersController {

    private final WorkerCommandService workerCommandService;
    private final WorkerQueryService workerQueryService;

    public WorkersController(
            WorkerCommandService workerCommandService,
            WorkerQueryService workerQueryService
    ) {
        this.workerCommandService = workerCommandService;
        this.workerQueryService = workerQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new worker")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worker created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "User not found"),
            @ApiResponse(responseCode = "409", description = "User already has a worker profile")})
    public ResponseEntity<WorkerResource> createWorker(@RequestBody CreateWorkerResource resource) {
        try {
            var createWorkerCommand = CreateWorkerCommandFromResourceAssembler.toCommandFromResource(resource);
            var worker = workerCommandService.handle(createWorkerCommand);
            if (worker.isEmpty()) return ResponseEntity.badRequest().build();
            var createdWorker = worker.get();
            var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(createdWorker);
            return new ResponseEntity<>(workerResource, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            // User does not exist
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            // User already has a worker profile
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @GetMapping("/{workerId}")
    @Operation(summary = "Get worker by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worker found"),
            @ApiResponse(responseCode = "404", description = "Worker not found")})
    public ResponseEntity<WorkerResource> getWorker(@PathVariable UUID workerId) {
        var worker = workerQueryService.handle(new GetWorkerByIdQuery(workerId));
        if (worker.isEmpty()) return ResponseEntity.notFound().build();
        var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(worker.get());
        return ResponseEntity.ok(workerResource);
    }

    @PostMapping("/{workerId}/services")
    @Operation(summary = "Add service to worker", description = "Add a new service to an existing worker")
    public ResponseEntity<WorkerResource> addWorkerService(
            @PathVariable UUID workerId,
            @Valid @RequestBody AddWorkerServiceResource resource) {
        var addServiceCommand = AddWorkerServiceCommandFromResourceAssembler.toCommandFromResource(workerId, resource);
        var worker = workerCommandService.handle(addServiceCommand);
        if (worker.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(worker.get());
        return ResponseEntity.ok(workerResource);
    }

    @DeleteMapping("/{workerId}/services")
    @Operation(summary = "Remove service from worker", description = "Remove a service from an existing worker")
    public ResponseEntity<WorkerResource> removeWorkerService(
            @PathVariable UUID workerId,
            @Valid @RequestBody AddWorkerServiceResource resource) {
        var removeServiceCommand = RemoveWorkerServiceCommandFromResourceAssembler.toCommandFromResource(workerId, resource);
        var worker = workerCommandService.handle(removeServiceCommand);
        if (worker.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var workerResource = WorkerResourceFromEntityAssembler.toResourceFromEntity(worker.get());
        return ResponseEntity.ok(workerResource);
    }
}