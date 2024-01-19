package io.srk.auth.service;

import io.srk.auth.model.auth.dto.UserDto;
import io.srk.auth.model.auth.enumeration.UserRole;
import io.srk.auth.model.auth.request.CreateUserRequest;

import java.util.List;
import java.util.UUID;

public interface AuthService {

    void create(CreateUserRequest request, UserRole role);

    void blockUser(UUID id);

    void unblockUser(UUID id);

    List<UserDto> getUsers();
}
