package io.srk.order.configuration.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FeignRequestAuthInterceptor implements RequestInterceptor {

    private static final Pattern BEARER_TOKEN_HEADER_PATTERN = Pattern.compile(
            "^Bearer [a-zA-Z0-9-._~+/]+=*$",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public void apply(RequestTemplate template) {
        var attributes = RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return;
        }
        if (attributes instanceof ServletRequestAttributes attrs) {
            String authHeader = attrs.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
            if (StringUtils.isBlank(authHeader)) {
                return;
            }
            Matcher matcher = BEARER_TOKEN_HEADER_PATTERN.matcher(authHeader);
            if (matcher.matches()) {
                template.header(HttpHeaders.AUTHORIZATION);
                template.header(HttpHeaders.AUTHORIZATION, authHeader);
            }
        }
    }
}