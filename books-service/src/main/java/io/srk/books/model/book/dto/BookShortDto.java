package io.srk.books.model.book.dto;

import io.srk.books.model.book.enumeration.BookStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookShortDto {

    private Long id;

    private String name;

    private BigDecimal price;

    private BookStatus status;
}
