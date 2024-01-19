package io.srk.auth.service.impl;

import io.srk.auth.exception.ClientException;
import io.srk.auth.exception.EntityNotFoundException;
import io.srk.auth.exception.ExceptionCode;
import io.srk.auth.model.auth.dto.UserDto;
import io.srk.auth.model.auth.entity.User;
import io.srk.auth.model.auth.enumeration.UserRole;
import io.srk.auth.model.auth.enumeration.UserStatus;
import io.srk.auth.model.auth.mapper.AuthMapper;
import io.srk.auth.model.auth.request.CreateUserRequest;
import io.srk.auth.repository.UserRepository;
import io.srk.auth.service.AuthService;
import io.srk.auth.service.SecurityService;
import io.srk.auth.util.EntityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final SecurityService securityService;
    private final AuthMapper authMapper;

    @Override
    public void create(CreateUserRequest request, UserRole role) {
        if (userRepository.existsByEmailOrUsername(request.getEmail(), request.getUsername())) {
            throw new ClientException(ExceptionCode.USER_ALREADY_EXIST);
        }
        var externalRequest = authMapper.toExternalRequest(request);
        UUID userId = securityService.createUser(externalRequest, role);
        if (role == UserRole.ADMIN) {
            return;
        }
        User entity = authMapper.toEntity(request);
        entity.setId(String.valueOf(userId));
        userRepository.save(entity);
    }

    @Override
    @Transactional
    public void blockUser(UUID id) {
        User entity = getEntity(id);
        if (entity.getStatus() == UserStatus.BLOCKED) {
            throw new ClientException(ExceptionCode.USER_WRONG_STATUS, UserStatus.ACTIVE);
        }
        securityService.blockUser(id);
        entity.setStatus(UserStatus.BLOCKED);
        userRepository.save(entity);
    }

    @Override
    public void unblockUser(UUID id) {
        User entity = getEntity(id);
        if (entity.getStatus() == UserStatus.ACTIVE) {
            throw new ClientException(ExceptionCode.USER_WRONG_STATUS, UserStatus.BLOCKED);
        }
        securityService.unblockUser(id);
        entity.setStatus(UserStatus.ACTIVE);
        userRepository.save(entity);
    }

    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll().stream()
                .map(authMapper::toDto)
                .toList();
    }

    private User getEntity(UUID id) {
        return userRepository.findById(id.toString())
                .orElseThrow(() -> new EntityNotFoundException(EntityConstants.USER, id));
    }
}
