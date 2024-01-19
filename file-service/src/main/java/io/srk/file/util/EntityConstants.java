package io.srk.file.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityConstants {

    FILE("file");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
