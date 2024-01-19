package io.srk.export.configuration.feign;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import io.srk.export.client.BookServiceClient;
import io.srk.export.client.FileServiceClient;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.support.FeignEncoderProperties;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(clients = {
        BookServiceClient.class,
        FileServiceClient.class
})
public class FeignConfiguration {

    @Bean
    public Encoder feignEncoder(
            FeignEncoderProperties properties,
            ObjectFactory<HttpMessageConverters> messageConverters,
            ObjectProvider<HttpMessageConverterCustomizer> customizers
    ) {
        return new CustomEncoder(new SpringFormEncoder(), messageConverters, properties, customizers);
    }
}
