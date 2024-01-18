package io.srk.books.configuration.feign;

import feign.RequestInterceptor;
import io.srk.books.client.DictionaryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients(clients = DictionaryClient.class)
@Configuration
public class FeignConfiguration {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return new FeignRequestAuthInterceptor();
    }
}
