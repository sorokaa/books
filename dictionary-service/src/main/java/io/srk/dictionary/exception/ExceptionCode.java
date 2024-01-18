package io.srk.dictionary.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionCode {

    ENTITY_DOES_NOT_EXIST("Entity %s does not exist by id %s");

    private final String value;

    public String formatted(Object... values) {
        return value.formatted(values);
    }
}
