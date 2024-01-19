package io.srk.export.configuration.feign;

import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.FeignEncoderProperties;
import org.springframework.cloud.openfeign.support.HttpMessageConverterCustomizer;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.lang.reflect.Type;

/**
 * Feign doesn't put multipart/form-data content type header in request.
 * Thus, this encoder solves this problem. I have no idea how it even works...
 * Been stolen here: <a href="https://blog.csdn.net/yangfeng20/article/details/128488104">...</a>
 */
public class CustomEncoder extends SpringEncoder {

    public CustomEncoder(
            SpringFormEncoder springFormEncoder,
            ObjectFactory<HttpMessageConverters> messageConverters,
            FeignEncoderProperties encoderProperties,
            ObjectProvider<HttpMessageConverterCustomizer> customizers
    ) {
        super(springFormEncoder, messageConverters, encoderProperties, customizers);
    }

    @Override
    public void encode(Object requestBody, Type bodyType, RequestTemplate request) throws EncodeException {
        if (request.methodMetadata().method().isAnnotationPresent(FormData.class)) {
            request.header(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);
        }
        super.encode(requestBody, bodyType, request);
    }
}
