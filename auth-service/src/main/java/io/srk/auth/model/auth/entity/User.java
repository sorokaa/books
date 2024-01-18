package io.srk.auth.model.auth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(schema = "auth_schema", name = "user")
public class User {

    @Id
    private String id;

    private String username;

    private String email;

    public UUID getId() {
        return UUID.fromString(id);
    }
}
