package io.srk.books.model.book.request;

import io.srk.books.model.book.enumeration.BookStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Update book request")
public class UpdateBookRequest extends AssignBookEntitiesRequest {

    @Schema(description = "Book name")
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Schema(description = "Book year")
    @NotNull(message = "Year cannot be null")
    private Short year;

    @Schema(description = "Book pages count")
    @NotNull(message = "Pages cannot be null")
    private Long pages;

    @Schema(description = "Book price")
    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @Schema(description = "Book status")
    @NotNull(message = "Status cannot be null")
    private BookStatus status;

    @Schema(description = "Book description")
    private String description;

    @Schema(description = "Book language dictionary id")
    @NotNull(message = "Language cannot be null")
    private Long languageId;

    @Schema(description = "Book categories")
    @NotEmpty(message = "Categories cannot be empty")
    private List<Long> categoryIds = new ArrayList<>();
}
