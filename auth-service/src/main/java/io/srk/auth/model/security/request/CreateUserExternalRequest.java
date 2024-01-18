package io.srk.auth.model.security.request;

import lombok.Data;

@Data
public class CreateUserExternalRequest {

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;
}
