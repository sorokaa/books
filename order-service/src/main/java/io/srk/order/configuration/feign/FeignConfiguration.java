package io.srk.order.configuration.feign;

import io.srk.order.client.BookServiceClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(clients = BookServiceClient.class)
@Configuration
public class FeignConfiguration {
}
