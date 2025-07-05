package com.techmove.fixnow.review.reviews.interfaces.rest;


import com.techmove.fixnow.review.reviews.application.services.*;
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
    public ResponseEntity<ReviewDTO> create(@RequestBody CreateReviewCommand command) {
        ReviewDTO dto = commandService.createReview(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @Operation(summary = "Listar todas las reseñas")
    @GetMapping
    public ResponseEntity<List<ReviewDTO>> getAll() {
        return ResponseEntity.ok(queryService.getAllReviews());
    }

    @Operation(summary = "Listar reseñas por trabajador")
    @GetMapping("/worker/{workerId}")
    public ResponseEntity<List<ReviewDTO>> getByWorker(@PathVariable Long workerId) {
        return ResponseEntity.ok(queryService.getReviewsByWorkerId(workerId));
    }
}
