package io.srk.order.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EntityConstants {

    ORDER("order");

    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
