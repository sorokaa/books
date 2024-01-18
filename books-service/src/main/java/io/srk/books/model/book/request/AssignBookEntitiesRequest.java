package io.srk.books.model.book.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "External Book entities")
public abstract class AssignBookEntitiesRequest {

    @Schema(description = "Book publisher id")
    @NotNull(message = "Publisher cannot be null")
    private Long publisherId;

    @Schema(description = "Book authors ids")
    @NotEmpty(message = "Authors cannot be empty")
    private List<Long> authorIds = new ArrayList<>();
}
