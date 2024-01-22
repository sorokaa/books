package io.srk.books.model.book.dto;

import io.srk.books.model.author.dto.AuthorShortDto;
import io.srk.books.model.publisher.dto.PublisherShortDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Book detailed information")
public class BookDto extends BookShortDto {

    @Schema(description = "Book language dictionary identifier")
    private Long languageId;

    @Schema(description = "Book year")
    private Short year;

    @Schema(description = "Book pages count")
    private Long pages;

    @Schema(description = "Book authors")
    private List<AuthorShortDto> authors;

    @Schema(description = "Book publisher")
    private PublisherShortDto publisher;

    @Schema(description = "Book categories")
    private List<Long> categoryIds = new ArrayList<>();

    private String description;

}
