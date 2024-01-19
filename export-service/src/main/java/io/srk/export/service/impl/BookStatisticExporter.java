package io.srk.export.service.impl;

import io.srk.export.client.BookServiceClient;
import io.srk.export.helper.impl.BookStatisticExportHelper;
import io.srk.export.model.export.dto.BookStatisticDto;
import io.srk.export.model.export.dto.BooksStatisticExportRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class BookStatisticExporter {

    private final BookServiceClient bookServiceClient;

    public byte[] export(BooksStatisticExportRequestDto request) {
        List<BookStatisticDto> statistic = bookServiceClient.getStatistic();
        request.setBooksStatistic(statistic);
        return BookStatisticExportHelper.export(request);
    }
}
