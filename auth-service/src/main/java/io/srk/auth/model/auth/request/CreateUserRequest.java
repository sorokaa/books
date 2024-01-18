package io.srk.auth.model.auth.request;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String username;

    private String email;

    private String password;
}
