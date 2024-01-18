package io.srk.books.model.publisher.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Update publisher request")
public class UpdatePublisherRequest {

    @Schema(description = "Publisher name")
    @NotBlank(message = "Name cannot be blank")
    private String name;
}
