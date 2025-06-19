package com.techmove.fixnow.users.interfaces.rest;

import com.techmove.fixnow.users.domain.services.UserCommandService;
import com.techmove.fixnow.users.interfaces.rest.resources.CreateUserResource;
import com.techmove.fixnow.users.interfaces.rest.resources.UserResource;
import com.techmove.fixnow.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import com.techmove.fixnow.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Available Users Endpoints")
public class UsersController {

    private final UserCommandService userCommandService;

    public UsersController(UserCommandService userCommandService) {
        this.userCommandService = userCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad request")})
    public ResponseEntity<UserResource> createProfile(@RequestBody CreateUserResource resource) {
        var createProfileCommand = CreateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(createProfileCommand);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var createdUser = user.get();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(createdUser);
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }
}
