package io.srk.books.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityConstants {

    BOOK("book"),
    AUTHOR("author"),
    PUBLISHER("publisher"),
    CATEGORY("category"),
    LANGUAGE("language");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
