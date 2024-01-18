package io.srk.books.model.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Author short information")
public class AuthorShortDto {

    @Schema(description = "Author id")
    private Long id;

    @Schema(description = "Author name")
    private String name;
}
