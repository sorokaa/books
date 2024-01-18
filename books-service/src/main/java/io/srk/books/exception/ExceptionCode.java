package io.srk.books.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionCode {

    NULL_VALUE_PROVIDED("Value %s must be provided"),
    DICTIONARY_NOT_FOUND("Dictionary %s not found by id %s"),
    ENTITY_DOES_NOT_EXIST("Entity %s does not exist by id %s");

    private final String value;

    public String formatted(Object... values) {
        return value.formatted(values);
    }
}
