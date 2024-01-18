package io.srk.auth.service;

import io.srk.auth.model.security.request.CreateUserExternalRequest;

import java.util.UUID;

public interface SecurityService {

    UUID createUser(CreateUserExternalRequest request);
}
