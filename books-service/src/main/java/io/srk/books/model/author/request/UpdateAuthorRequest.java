package io.srk.books.model.author.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Author update request")
public class UpdateAuthorRequest {

    @Schema(description = "Author name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Author biography")
    private String biography;
}
