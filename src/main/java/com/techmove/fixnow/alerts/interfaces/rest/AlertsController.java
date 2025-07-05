package com.techmove.fixnow.alerts.interfaces.rest;

import com.techmove.fixnow.alerts.application.services.commandservices.AlertCommandService;
import com.techmove.fixnow.alerts.application.services.queryservices.AlertQueryService;
import com.techmove.fixnow.alerts.domain.model.commands.CreateAlertCommand;
import com.techmove.fixnow.alerts.domain.model.queries.GetAlertsByUserIdQuery;
import com.techmove.fixnow.alerts.interfaces.rest.resources.AlertResource;
import com.techmove.fixnow.alerts.interfaces.rest.resources.CreateAlertResource;
import com.techmove.fixnow.alerts.interfaces.rest.transform.AlertResourceFromEntityAssembler;
import com.techmove.fixnow.alerts.interfaces.rest.transform.CreateAlertCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Alerts", description = "Available Alerts Endpoints")
@RestController
@RequestMapping("/api/v1/alerts")
public class AlertsController {

    private final AlertCommandService alertCommandService;
    private final AlertQueryService alertQueryService;

    public AlertsController(AlertCommandService alertCommandService, AlertQueryService alertQueryService) {
        this.alertCommandService = alertCommandService;
        this.alertQueryService = alertQueryService;
    }

    @PostMapping
    @Operation(summary = "Create a new alert")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Alert created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<AlertResource> createAlert(@RequestBody CreateAlertResource resource) {
        CreateAlertCommand command = CreateAlertCommandFromResourceAssembler.toCommandFromResource(resource);
        AlertResource createdAlertResource = AlertResourceFromEntityAssembler.toResourceFromEntity(alertCommandService.handle(command));
        return new ResponseEntity<>(createdAlertResource, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get alerts by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alerts found"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    public ResponseEntity<List<AlertResource>> getAlertsByUserId(@PathVariable Long userId) {
        List<AlertResource> alerts = alertQueryService.handle(new GetAlertsByUserIdQuery(userId))
                .stream()
                .map(AlertResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(alerts);
    }

    @GetMapping("/user/{userId}/unread")
    @Operation(summary = "Get unread alerts by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Unread alerts found"),
            @ApiResponse(responseCode = "404", description = "User not found")})
    public ResponseEntity<List<AlertResource>> getUnreadAlertsByUserId(@PathVariable Long userId) {
        List<AlertResource> unreadAlerts = alertQueryService.getUnreadAlertsByUserId(userId)
                .stream()
                .map(AlertResourceFromEntityAssembler::toResourceFromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(unreadAlerts);
    }

    @PutMapping("/{alertId}/read")
    @Operation(summary = "Mark alert as read")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Alert marked as read"),
            @ApiResponse(responseCode = "404", description = "Alert not found")})
    public ResponseEntity<Void> markAlertAsRead(@PathVariable Long alertId) {
        alertCommandService.markAlertAsRead(alertId);
        return ResponseEntity.noContent().build();
    }
}
