package com.techmove.fixnow.users.interfaces.rest;

import com.techmove.fixnow.users.domain.model.queries.GetAllWorkerCategoriesQuery;
import com.techmove.fixnow.users.domain.model.queries.GetWorkerCategoryByIdQuery;
import com.techmove.fixnow.users.domain.model.queries.GetWorkerCategoryBySlugQuery;
import com.techmove.fixnow.users.domain.services.WorkerCategoryCommandService;
import com.techmove.fixnow.users.domain.services.WorkerCategoryQueryService;
import com.techmove.fixnow.users.interfaces.rest.resources.CreateWorkerCategoryResource;
import com.techmove.fixnow.users.interfaces.rest.resources.WorkerCategoryResource;
import com.techmove.fixnow.users.interfaces.rest.transform.CreateWorkerCategoryFromSourceAssembler;
import com.techmove.fixnow.users.interfaces.rest.transform.WorkerCategoryFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Worker Categories", description = "Available Worker Categories Endpoints")
public class WorkerCategoryController {

    private final WorkerCategoryCommandService workerCategoryCommandService;
    private final WorkerCategoryQueryService workerCategoryQueryService;

    public WorkerCategoryController(
            WorkerCategoryCommandService workerCategoryCommandService,
            WorkerCategoryQueryService workerCategoryQueryService
    ) {
        this.workerCategoryCommandService = workerCategoryCommandService;
        this.workerCategoryQueryService = workerCategoryQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new worker category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Worker category created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<WorkerCategoryResource> createWorkerCategory(@RequestBody CreateWorkerCategoryResource resource) {
        var createWorkerCategoryCommand = CreateWorkerCategoryFromSourceAssembler.toCommandFormSource(resource);
        var workerCategory = workerCategoryCommandService.handle(createWorkerCategoryCommand);
        if (workerCategory.isEmpty()) return ResponseEntity.badRequest().build();
        var createdWorkerCategory = workerCategory.get();
        var workerCategoryResource = WorkerCategoryFromEntityAssembler.toResourceFromEntity(createdWorkerCategory);
        return new ResponseEntity<>(workerCategoryResource, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all worker categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worker categories found"),
            @ApiResponse(responseCode = "404", description = "Worker categories not found")})
    public ResponseEntity<List<WorkerCategoryResource>> getAllWorkerCategories() {
        var workerCategories = workerCategoryQueryService.handle(new GetAllWorkerCategoriesQuery());
        if (workerCategories.isEmpty()) return ResponseEntity.notFound().build();
        var workerCategoryResources = workerCategories.get().stream()
                .map(WorkerCategoryFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(workerCategoryResources);
    }

    @GetMapping("/{slug}")
    @Operation(summary = "Get worker category by slug")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worker category found"),
            @ApiResponse(responseCode = "404", description = "Worker category not found")})
    public ResponseEntity<WorkerCategoryResource> getWorkerCategoryBySlug(@PathVariable String slug) {
        var workerCategory = workerCategoryQueryService.handle(new GetWorkerCategoryBySlugQuery(slug));
        if (workerCategory.isEmpty()) return ResponseEntity.notFound().build();
        var workerCategoryResource = WorkerCategoryFromEntityAssembler.toResourceFromEntity(workerCategory.get());
        return ResponseEntity.ok(workerCategoryResource);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Get worker category by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Worker category found"),
            @ApiResponse(responseCode = "404", description = "Worker category not found")})
    public ResponseEntity<WorkerCategoryResource> getWorkerCategoryById(@PathVariable Long id) {
        var workerCategory = workerCategoryQueryService.handle(new GetWorkerCategoryByIdQuery(id));
        if (workerCategory.isEmpty()) return ResponseEntity.notFound().build();
        var workerCategoryResource = WorkerCategoryFromEntityAssembler.toResourceFromEntity(workerCategory.get());
        return ResponseEntity.ok(workerCategoryResource);
    }
}
