package io.srk.auth.service.impl;

import io.srk.auth.exception.ClientException;
import io.srk.auth.exception.ExceptionCode;
import io.srk.auth.model.auth.entity.User;
import io.srk.auth.model.auth.enumeration.UserRole;
import io.srk.auth.model.auth.mapper.AuthMapper;
import io.srk.auth.model.auth.request.CreateUserRequest;
import io.srk.auth.repository.UserRepository;
import io.srk.auth.service.AuthService;
import io.srk.auth.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final AuthMapper authMapper;

    @Override
    public void create(CreateUserRequest request, UserRole role) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ClientException(ExceptionCode.USER_ALREADY_EXIST);
        }
        var externalRequest = authMapper.toExternalRequest(request);
        UUID userId = securityService.createUser(externalRequest, role);
        User entity = authMapper.toEntity(request);
        entity.setId(String.valueOf(userId));
        userRepository.save(entity);
    }
}
