package io.srk.books.model.author.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Author search filter")
public class AuthorFilter {

    @Schema(description = "Author name")
    private String name;
}
