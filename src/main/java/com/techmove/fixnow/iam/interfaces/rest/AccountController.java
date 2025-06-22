package com.techmove.fixnow.iam.interfaces.rest;

import com.techmove.fixnow.iam.application.AccountCommandService;
import com.techmove.fixnow.iam.domain.model.aggregates.Account;
import com.techmove.fixnow.iam.domain.model.commands.SignUpCommand;
import com.techmove.fixnow.iam.domain.model.commands.SignInCommand;
import com.techmove.fixnow.iam.application.AccountSignInService;
import com.techmove.fixnow.iam.infrastructure.tokens.jwt.BearerTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Authentication", description = "Available Auth Endpoints")
@RequestMapping(value = "/api/v1/accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private final AccountCommandService accountCommandService;
    private final AccountSignInService accountSignInService;
    private final BearerTokenService tokenService;

    public AccountController(AccountCommandService accountCommandService, AccountSignInService accountSignInService, BearerTokenService tokenService) {
        this.accountCommandService = accountCommandService;
        this.accountSignInService = accountSignInService;
        this.tokenService = tokenService;
    }

    @PostMapping("/signup")
    @Operation(summary = "Sign up a new account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data or account already exists")
    })
    public ResponseEntity<String> register(@RequestBody SignUpRequest request) {
        Account account = accountCommandService.registerAccount(
                new SignUpCommand(request.email(), request.password(), request.role())
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Account created successfully with ID: " + account.getId());
    }

    @PostMapping("/signin")
    @Operation(summary = "Sign in to an existing account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials or unauthorized access")
    })
    public ResponseEntity<JwtResponse> signIn(@RequestBody SignInRequest request) {
        Account account = accountSignInService.signIn(
                new SignInCommand(request.email(), request.password())
        );
        String token = tokenService.generateToken(account.getEmail());
        return ResponseEntity.ok(new JwtResponse(account.getId().toString(), token));
    }

    public record SignUpRequest(String email, String password, Account.Role role) {}

    public record SignInRequest(String email, String password) {}

    public record JwtResponse(String uid, String token) {}
}