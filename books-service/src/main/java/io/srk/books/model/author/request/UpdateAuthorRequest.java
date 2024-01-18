package io.srk.books.model.author.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateAuthorRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String biography;
}
