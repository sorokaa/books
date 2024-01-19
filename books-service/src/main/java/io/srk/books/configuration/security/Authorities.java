package io.srk.books.configuration.security;

import org.springframework.stereotype.Component;

@Component
public final class Authorities {

    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_CLIENT = "ROLE_CLIENT";

    public String getRoleAdmin() {
        return ROLE_ADMIN;
    }

    public String getRoleClient() {
        return ROLE_CLIENT;
    }
}
