package io.srk.auth.model.auth.entity;

import io.srk.auth.model.auth.enumeration.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(schema = "auth_schema", name = "user")
public class User {

    @Id
    private String id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    @Column(columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    public UUID getId() {
        return UUID.fromString(id);
    }
}
