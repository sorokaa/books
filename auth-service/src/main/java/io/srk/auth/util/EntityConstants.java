package io.srk.auth.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityConstants {

    USER("user");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
