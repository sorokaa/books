package io.srk.export.model.export.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BookStatisticDto {

    private String bookName;

    private String authorName;

    private Short year;

    private BigDecimal price;

    private Long orders;
}
