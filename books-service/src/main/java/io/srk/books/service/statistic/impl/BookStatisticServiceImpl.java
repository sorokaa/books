package io.srk.books.service.statistic.impl;

import io.srk.books.client.OrderServiceClient;
import io.srk.books.model.book.dto.BookStatisticDto;
import io.srk.books.model.book.mapper.BookMapper;
import io.srk.books.repository.book.BookRepository;
import io.srk.books.service.statistic.BookStatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookStatisticServiceImpl implements BookStatisticService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final OrderServiceClient orderServiceClient;

    @Override
    public List<BookStatisticDto> getStatistic() {
        List<BookStatisticDto> statistics = bookRepository.findAll().stream()
                .map(bookMapper::toStatisticDto)
                .toList();
        Set<Long> bookIds = statistics.stream()
                .map(BookStatisticDto::getId)
                .collect(Collectors.toSet());
        Map<String, Long> booksOrders = orderServiceClient.getBookOrdersCount(bookIds);
        for (BookStatisticDto statistic : statistics) {
            String id = String.valueOf(statistic.getId());
            Long count = booksOrders.getOrDefault(id, 0L);
            statistic.setOrders(count);
        }
        return statistics;
    }
}
