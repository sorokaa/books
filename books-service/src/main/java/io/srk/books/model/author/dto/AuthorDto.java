package io.srk.books.model.author.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Author detailed information")
public class AuthorDto extends AuthorShortDto {

    @Schema(description = "Author biography")
    private String biography;
}
