package io.srk.auth.model.auth.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private UUID id;

    private String username;

    private String email;

    private String firstName;

    private String lastName;
}
