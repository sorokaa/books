package io.srk.auth.model.auth.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {

    CLIENT("CLIENT_GROUP"),
    ADMIN("ADMIN_GROUP");

    private final String group;
}
