package io.srk.dictionary.model.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Category dictionary data")
public class CategoryDto {

    @Schema(description = "Category id")
    private Long id;

    @Schema(description = "Category value")
    private String value;
}
