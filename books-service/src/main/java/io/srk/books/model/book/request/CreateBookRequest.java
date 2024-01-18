package io.srk.books.model.book.request;

import io.srk.books.model.book.enumeration.BookStatus;
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
public class CreateBookRequest extends AssignBookEntitiesRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotNull(message = "Year cannot be null")
    private Short year;

    @NotNull(message = "Pages cannot be null")
    private Long pages;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @NotNull(message = "Status cannot be null")
    private BookStatus status;

    private String description;

    @NotNull(message = "Language cannot be null")
    private Long languageId;

    @NotEmpty(message = "Categories cannot be empty")
    private List<Long> categoryIds = new ArrayList<>();
}
