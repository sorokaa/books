package io.srk.auth.service;

import io.srk.auth.model.auth.enumeration.UserRole;
import io.srk.auth.model.auth.request.CreateUserRequest;

public interface AuthService {

    void create(CreateUserRequest request, UserRole role);
}