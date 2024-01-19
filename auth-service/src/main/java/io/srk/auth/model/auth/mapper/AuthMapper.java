package io.srk.auth.model.auth.mapper;

import io.srk.auth.model.auth.dto.UserDto;
import io.srk.auth.model.auth.entity.User;
import io.srk.auth.model.auth.request.CreateUserRequest;
import io.srk.auth.model.security.request.CreateUserExternalRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface AuthMapper {

    CreateUserExternalRequest toExternalRequest(CreateUserRequest request);

    User toEntity(CreateUserRequest request);

    UserDto toDto(User user);
}
