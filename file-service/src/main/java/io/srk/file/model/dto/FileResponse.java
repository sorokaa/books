package io.srk.file.model.dto;

import lombok.Data;

@Data
public class FileResponse {

    private String name;

    private String extension;

    private byte[] content;

    public String getFullName() {
        return "%s.%s".formatted(name, extension);
    }
}
