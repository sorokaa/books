package io.srk.books.model.publisher.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreatePublisherRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;
}
