package com.techmove.fixnow.review.interfaces.rest;

import com.techmove.fixnow.review.domain.model.queries.GetAllReviewsQuery;
import com.techmove.fixnow.review.domain.model.queries.GetReviewsByWorkerIdQuery;
import com.techmove.fixnow.review.domain.services.ReviewCommandService;
import com.techmove.fixnow.review.domain.services.ReviewQueryService;
import com.techmove.fixnow.review.interfaces.rest.resources.CreateReviewResource;
import com.techmove.fixnow.review.interfaces.rest.resources.ReviewResource;
import com.techmove.fixnow.review.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@Tag(name = "User Reviews")
public class ReviewController {
    private final ReviewCommandService commandService;
    private final ReviewQueryService queryService;

    public ReviewController(ReviewCommandService commandService, ReviewQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(summary = "Crear reseña")
    @PostMapping
    public ResponseEntity<ReviewResource> create(@RequestBody CreateReviewResource resource) {
        var command = CreateReviewCommandFromResourceAssembler.toCommandFromResource(resource);
        ReviewResource reviewResource = commandService.createReview(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewResource);
    }

    @Operation(summary = "Listar todas las reseñas")
    @GetMapping
    public ResponseEntity<List<ReviewResource>> getAll() {
        var query = new GetAllReviewsQuery();
        return ResponseEntity.ok(queryService.handle(query));
    }

    @Operation(summary = "Listar reseñas por trabajador")
    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<ReviewResource>> getByWorker(@PathVariable Long workerId) {
        var query = new GetReviewsByWorkerIdQuery(workerId);
        return ResponseEntity.ok(queryService.handle(query));
    }
}
