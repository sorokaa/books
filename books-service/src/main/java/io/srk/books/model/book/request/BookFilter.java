package io.srk.books.model.book.request;

import io.srk.books.model.book.enumeration.BookStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Schema(description = "Book search filter")
public class BookFilter {

    @Schema(description = "Book name (search like)")
    private String name;

    @Schema(description = "Books statuses")
    private List<BookStatus> statuses;

    @Schema(description = "Books year range")
    private Range<Short> yearRange;

    @Schema(description = "Books price range")
    private Range<BigDecimal> priceRange;

    @Schema(description = "Books pages count range")
    private Range<Long> pagesRange;

    @Schema(description = "Books publishers")
    private List<Long> publisherIds = new ArrayList<>();

    @Schema(description = "Books categories")
    private List<Long> categoryIds = new ArrayList<>();

    @Schema(description = "Books languages")
    private List<Long> languageIds = new ArrayList<>();

    @Schema(description = "Books authors")
    private List<Long> authorIds = new ArrayList<>();

    @Data
    @Schema(description = "Range filter")
    public static class Range<T extends Comparable<T>> {

        @Schema(description = "Minimal comparable value")
        T minValue;

        @Schema(description = "Maximal comparable value")
        T maxValue;
    }
}
