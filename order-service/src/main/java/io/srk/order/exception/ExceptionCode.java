package io.srk.order.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionCode {

    ENTITY_DOES_NOT_EXIST("Entity %s does not exist by id %s"),
    BOOK_HAS_WRONG_STATUS("Can not order status with such status");

    private final String value;

    public String formatted(Object... values) {
        return value.formatted(values);
    }
}
