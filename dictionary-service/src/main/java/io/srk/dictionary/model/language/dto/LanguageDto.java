package io.srk.dictionary.model.language.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Language dictionary data")
public class LanguageDto {

    @Schema(description = "Language id")
    private Long id;

    @Schema(description = "Language value")
    private String value;
}
