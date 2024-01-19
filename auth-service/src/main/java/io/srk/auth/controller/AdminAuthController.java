package io.srk.auth.controller;

import io.srk.auth.model.auth.dto.UserDto;
import io.srk.auth.model.auth.enumeration.UserRole;
import io.srk.auth.model.auth.request.CreateUserRequest;
import io.srk.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/auth/admin")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
@Tag(name = "Admin Auth API")
public class AdminAuthController {

    private final AuthService authService;

    @Operation(summary = "Create admin user")
    @PostMapping
    public void create(@Valid @RequestBody CreateUserRequest request) {
        log.debug("API request to create admin. Request: {}", request);
        authService.create(request, UserRole.ADMIN);
    }

    @Operation(summary = "Get users")
    @GetMapping
    public List<UserDto> getUsers() {
        log.debug("API request to get all users");
        return authService.getUsers();
    }

    @Operation(summary = "Block user")
    @PatchMapping("/{id}/block")
    public void blockUser(@PathVariable UUID id) {
        log.debug("API request to block user with id {}", id);
        authService.blockUser(id);
    }

    @Operation(summary = "Unblock user")
    @PatchMapping("/{id}/unblock")
    public void unblockUser(@PathVariable UUID id) {
        log.debug("API request to unblock user with id {}", id);
        authService.unblockUser(id);
    }
}
