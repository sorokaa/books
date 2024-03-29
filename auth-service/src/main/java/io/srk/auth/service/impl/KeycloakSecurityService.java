package io.srk.auth.service.impl;

import io.srk.auth.model.auth.enumeration.UserRole;
import io.srk.auth.model.keycloak.mapper.KeycloakMapper;
import io.srk.auth.model.security.request.CreateUserExternalRequest;
import io.srk.auth.service.SecurityService;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class KeycloakSecurityService implements SecurityService {

    private final RealmResource realm;
    private final KeycloakMapper mapper;

    @Override
    public UUID createUser(CreateUserExternalRequest request, UserRole role) {
        var userRepresentation = mapper.toUserRepresentation(request);
        userRepresentation.setCredentials(List.of(createCredentials(request.getPassword())));
        userRepresentation.setEnabled(true);
        userRepresentation.setGroups(List.of(role.getGroup()));
        Response response = realm.users().create(userRepresentation);
        String userId = CreatedResponseUtil.getCreatedId(response);
        return UUID.fromString(userId);
    }

    @Override
    public void blockUser(UUID id) {
        UserResource userResource = realm.users().get(id.toString());
        UserRepresentation representation = userResource.toRepresentation();
        representation.setEnabled(false);
        userResource.update(representation);
    }

    @Override
    public void unblockUser(UUID id) {
        UserResource userResource = realm.users().get(id.toString());
        UserRepresentation representation = userResource.toRepresentation();
        representation.setEnabled(true);
        userResource.update(representation);
    }

    private CredentialRepresentation createCredentials(String password) {
        CredentialRepresentation representation = new CredentialRepresentation();
        representation.setTemporary(false);
        representation.setType(CredentialRepresentation.PASSWORD);
        representation.setValue(password);
        return representation;
    }
}
