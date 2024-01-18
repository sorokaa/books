package io.srk.auth.model.keycloak.mapper;

import io.srk.auth.model.security.request.CreateUserExternalRequest;
import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface KeycloakMapper {

    UserRepresentation toUserRepresentation(CreateUserExternalRequest request);
}
