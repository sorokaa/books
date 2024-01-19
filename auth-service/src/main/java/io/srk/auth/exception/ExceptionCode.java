package io.srk.auth.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ExceptionCode {

    ENTITY_DOES_NOT_EXIST("Entity %s does not exist by id %s"),
    USER_ALREADY_EXIST("User already exist"),
    USER_WRONG_STATUS("User has wrong status, must be %s");

    private final String value;

    public String formatted(Object... values) {
        return value.formatted(values);
    }
}
