package io.srk.export.client;

import io.srk.export.model.export.dto.BookStatisticDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "book-service-client", url = "${client.book-service-url}/api/books")
public interface BookServiceClient {

    @GetMapping("/statistic")
    List<BookStatisticDto> getStatistic();
}
