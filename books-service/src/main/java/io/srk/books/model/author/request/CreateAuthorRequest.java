package io.srk.books.model.author.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAuthorRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    private String biography;
}
