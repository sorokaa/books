package io.srk.order.configuration.feign;

import feign.RequestInterceptor;
import io.srk.order.client.BookServiceClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(clients = BookServiceClient.class)
@Configuration
public class FeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestAuthInterceptor();
    }
}
