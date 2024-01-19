package io.srk.file.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Data
@Entity
@Table(schema = "file_schema", name = "file_entity")
public class FileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String path;

    private String name;

    private String extension;

    private Long size;

    @CreationTimestamp
    private Instant created;

    public String getFullPath() {
        return "%s/%s.%s".formatted(path, name, extension);
    }

    public String getFullFileName() {
        return "%s.%s".formatted(name, extension);
    }
}
