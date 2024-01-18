package io.srk.dictionary.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityConstants {

    CATEGORY("category"),
    LANGUAGE("language");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
