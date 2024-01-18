package io.srk.order.client;

import io.srk.order.model.external.book.BookDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "book-service-client", url = "${client.book-service-url}/api/books")
public interface BookServiceClient {

    @GetMapping("/{id}")
    BookDto getById(@PathVariable Long id);
}
