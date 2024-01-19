package io.srk.auth.controller;

import io.srk.auth.model.auth.enumeration.UserRole;
import io.srk.auth.model.auth.request.CreateUserRequest;
import io.srk.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
@Tag(name = "Admin Auth API")
public class AdminAuthController {

    private final AuthService authService;

    @Operation(summary = "Create admin user")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping
    public void create(@RequestBody CreateUserRequest request) {
        authService.create(request, UserRole.ADMIN);
    }
}
