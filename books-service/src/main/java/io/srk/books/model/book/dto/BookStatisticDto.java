package io.srk.books.model.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookStatisticDto {

    private Long id;

    private String bookName;

    private String authorName;

    private Short year;

    private BigDecimal price;

    private Long orders;
}
