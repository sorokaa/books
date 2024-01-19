package io.srk.auth.controller;

import io.srk.auth.model.auth.enumeration.UserRole;
import io.srk.auth.model.auth.request.CreateUserRequest;
import io.srk.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/client")
@RequiredArgsConstructor
@Tag(name = "Client Auth API")
public class ClientAuthController {

    private final AuthService authService;

    @Operation(summary = "Create user")
    @PostMapping
    public void create(@RequestBody CreateUserRequest request) {
        authService.create(request, UserRole.CLIENT);
    }
}
