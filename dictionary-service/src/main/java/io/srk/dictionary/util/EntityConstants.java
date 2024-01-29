package io.srk.dictionary.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum EntityConstants {

    CATEGORY("category"),
    LANGUAGE("language");

    private final String name;

    public static EntityConstants findByName(String tableName) {
        return Arrays.stream(values())
                .filter(entity -> Objects.equals(tableName, entity.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return name;
    }
}
