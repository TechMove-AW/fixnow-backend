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
    @Operation(
            summary = "Register a new user account",
            description = "Creates a new user account with the provided email, password, and role. Returns the created account information including the generated ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Account created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data (missing required fields, invalid email format, weak password)"),
            @ApiResponse(responseCode = "409", description = "Account with this email already exists")
    })
    public ResponseEntity<AccountResponse> register(@RequestBody SignUpRequest request) {
        Account account = accountCommandService.registerAccount(
                new SignUpCommand(request.email(), request.password(), request.role())
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AccountResponse(account.getId(), account.getEmail(), account.getRole()));
    }

    @PostMapping("/signin")
    @Operation(
            summary = "Authenticate user and generate access token",
            description = "Validates user credentials and returns a JWT token for authenticated access to protected endpoints."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authentication successful, JWT token generated"),
            @ApiResponse(responseCode = "401", description = "Invalid email or password"),
            @ApiResponse(responseCode = "404", description = "Account not found")
    })
    public ResponseEntity<JwtResponse> signIn(@RequestBody SignInRequest request) {
        Account account = accountSignInService.signIn(
                new SignInCommand(request.email(), request.password())
        );
        String token = tokenService.generateToken(account.getEmail());
        return ResponseEntity.ok(new JwtResponse(account.getId(), token));
    }

    /**
     * Request payload for user registration
     * @param email User's email address (must be valid format)
     * @param password User's password (minimum 8 characters recommended)
     * @param role User's role in the system (CLIENT or WORKER)
     */
    public record SignUpRequest(String email, String password, Account.Role role) {}

    /**
     * Request payload for user authentication
     * @param email User's registered email address
     * @param password User's password
     */
    public record SignInRequest(String email, String password) {}

    /**
     * Response containing the created account information
     * @param id Generated account ID
     * @param email Account email address
     * @param role Account role
     */
    public record AccountResponse(Long id, String email, Account.Role role) {}

    /**
     * Response containing authentication token
     * @param id Account ID
     * @param token JWT access token for API authentication
     */
    public record JwtResponse(Long id, String token) {}
}