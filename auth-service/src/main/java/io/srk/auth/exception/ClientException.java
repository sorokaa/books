package io.srk.auth.exception;

import lombok.Getter;

@Getter
public class ClientException extends RuntimeException {

    private final ExceptionCode reason;
    private final String message;

    public ClientException(ExceptionCode reason, Object... values) {
        this.message = reason.formatted(values);
        this.reason = reason;
    }
}