package io.srk.order.model.external.book;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookDto {

    private Long id;

    private BigDecimal price;

    private BookStatus status;
}
