package io.srk.export.model.export.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BooksStatisticExportRequestDto extends ExportRequestDto {

    private List<BookStatisticDto> booksStatistic;
}

