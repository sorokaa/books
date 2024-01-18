package io.srk.books.model.publisher.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Create request for publisher")
public class CreatePublisherRequest {

    @Schema(description = "Publisher name")
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
