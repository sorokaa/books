package io.srk.books.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;
import java.util.Set;

@FeignClient(name = "order-service-client", url = "${client.order-service-url}/api/orders")
public interface OrderServiceClient {

    @GetMapping("/count")
    Map<String, Long> getBookOrdersCount(@RequestParam("bookIds") Set<Long> bookIds);
}
