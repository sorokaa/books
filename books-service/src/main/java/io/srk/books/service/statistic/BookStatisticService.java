package io.srk.books.service.statistic;

import io.srk.books.model.book.dto.BookStatisticDto;

import java.util.List;

public interface BookStatisticService {

    List<BookStatisticDto> getStatistic();
}
