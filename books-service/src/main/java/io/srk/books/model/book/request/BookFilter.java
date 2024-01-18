package io.srk.books.model.book.request;

import io.srk.books.model.book.enumeration.BookStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class BookFilter {

    private String name;

    private List<BookStatus> statuses;

    private Range<Short> yearRange;

    private Range<BigDecimal> priceRange;

    private Range<Long> pagesRange;

    private List<Long> publisherIds = new ArrayList<>();

    private List<Long> categoryIds = new ArrayList<>();

    private List<Long> languageIds = new ArrayList<>();

    private List<Long> authorIds = new ArrayList<>();

    @Data
    public static class Range<T extends Comparable<T>> {

        T minValue;

        T maxValue;
    }
}
