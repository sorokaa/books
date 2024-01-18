package io.srk.books.model.book.dto;

import io.srk.books.model.book.enumeration.BookStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "Book short information")
public class BookShortDto {

    @Schema(description = "Book id")
    private Long id;

    @Schema(description = "Book name")
    private String name;

    @Schema(description = "Book price")
    private BigDecimal price;

    @Schema(description = "Book description")
    private BookStatus status;
}
