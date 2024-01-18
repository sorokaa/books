package io.srk.books.model.book.mapper;

import io.srk.books.model.author.mapper.AuthorMapper;
import io.srk.books.model.author.mapper.ShortAuthorInfo;
import io.srk.books.model.book.dto.BookDto;
import io.srk.books.model.book.dto.BookShortDto;
import io.srk.books.model.book.entity.Book;
import io.srk.books.model.book.request.CreateBookRequest;
import io.srk.books.model.book.request.UpdateBookRequest;
import io.srk.books.model.publisher.PublisherMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

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
    BookDto toDto(Book book);

    BookShortDto toShortDto(Book book);

    Book toEntity(CreateBookRequest request);

    Book update(@MappingTarget Book toUpdate, UpdateBookRequest request);
}
