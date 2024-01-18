package io.srk.auth.service.impl;

import io.srk.auth.model.security.request.CreateUserExternalRequest;
import io.srk.auth.model.keycloak.mapper.KeycloakMapper;
import io.srk.auth.service.SecurityService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeycloakSecurityService implements SecurityService {

    private final RealmResource realm;
    private final KeycloakMapper mapper;

    @Override
    public UUID createUser(CreateUserExternalRequest request) {
        var userRepresentation = mapper.toUserRepresentation(request);
        userRepresentation.setCredentials(List.of(createCredentials(request.getPassword())));
        userRepresentation.setEnabled(true);
        Response response = realm.users().create(userRepresentation);
        String userId = CreatedResponseUtil.getCreatedId(response);
        return UUID.fromString(userId);
    }

    private CredentialRepresentation createCredentials(String password) {
        CredentialRepresentation representation = new CredentialRepresentation();
        representation.setTemporary(false);
        representation.setType(CredentialRepresentation.PASSWORD);
        representation.setValue(password);
        return representation;
    }
}
