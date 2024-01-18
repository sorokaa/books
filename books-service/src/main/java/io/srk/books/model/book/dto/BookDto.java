package io.srk.books.model.book.dto;

import io.srk.books.model.author.dto.AuthorShortDto;
import io.srk.books.model.publisher.dto.PublisherShortDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class BookDto extends BookShortDto {

    private Long languageId;

    private Short year;

    private Long pages;

    private List<AuthorShortDto> authors;

    private PublisherShortDto publisher;

    private List<Long> categoryIds = new ArrayList<>();

}
