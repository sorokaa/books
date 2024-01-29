package io.srk.books.model.book.mapper;

import io.srk.books.model.author.entity.Author;
import io.srk.books.model.author.mapper.AuthorMapper;
import io.srk.books.model.author.mapper.ShortAuthorInfo;
import io.srk.books.model.book.dto.BookDto;
import io.srk.books.model.book.dto.BookShortDto;
import io.srk.books.model.book.dto.BookStatisticDto;
import io.srk.books.model.book.entity.Book;
import io.srk.books.model.book.request.CreateBookRequest;
import io.srk.books.model.book.request.UpdateBookRequest;
import io.srk.books.model.publisher.mapper.PublisherMapper;
import io.srk.books.model.publisher.mapper.ShortPublisherInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        uses = {
                AuthorMapper.class,
                PublisherMapper.class
        },
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BookMapper {

    @Mapping(target = "authors", qualifiedBy = ShortAuthorInfo.class)
    @Mapping(target = "publisher", qualifiedBy = ShortPublisherInfo.class)
    BookDto toDto(Book book);

    @Mapping(target = "authorsNames", expression = "java(getAuthorsNames(book.getAuthors()))")
    BookShortDto toShortDto(Book book);

    Book toEntity(CreateBookRequest request);

    Book update(@MappingTarget Book toUpdate, UpdateBookRequest request);

    @Mapping(target = "bookName", source = "name")
    @Mapping(target = "authorName", expression = "java(getAuthorsNamesConcat(book.getAuthors()))")
    BookStatisticDto toStatisticDto(Book book);

    default String getAuthorsNamesConcat(List<Author> authors) {
        if (CollectionUtils.isEmpty(authors)) {
            return StringUtils.EMPTY;
        }
        return authors.stream()
                .map(Author::getName)
                .collect(Collectors.joining(", "));
    }

    default List<String> getAuthorsNames(List<Author> authors) {
        if (CollectionUtils.isEmpty(authors)) {
            return new ArrayList<>();
        }
        return authors.stream()
                .map(Author::getName)
                .toList();
    }
}
