package io.srk.file.model.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "File information")
public class FileDto {

    @Schema(description = "Internal file id")
    private Long id;

    @Schema(description = "File name")
    private String name;

    @Schema(description = "File extension")
    private String extension;

    @Schema(description = "File size")
    private Long size;
}
