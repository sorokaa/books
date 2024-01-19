package io.srk.auth.controller.handler;

import io.srk.auth.exception.ClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ApiErrorsHandler {

    @ExceptionHandler
    public ResponseEntity<Object> clientException(ClientException exception) {
        Map<String, Object> body = Map.of(
                "message", exception.getMessage(),
                "code", exception.getReason(),
                "timestamp", Instant.now()
        );
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler
    public ResponseEntity<Object> methodArgumentNotValid(MethodArgumentNotValidException exception) {
        List<String> errors = exception.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
        Map<String, Object> body = Map.of(
                "message", "Invalid values provided",
                "errors", errors,
                "timestamp", Instant.now()
        );
        return ResponseEntity.badRequest().body(body);
    }
}
