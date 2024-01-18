package io.srk.books.model.author.mapper;

import io.srk.books.model.author.dto.AuthorDto;
import io.srk.books.model.author.dto.AuthorShortDto;
import io.srk.books.model.author.entity.Author;
import io.srk.books.model.author.request.CreateAuthorRequest;
import io.srk.books.model.author.request.UpdateAuthorRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface AuthorMapper {

    @ShortAuthorInfo
    AuthorShortDto toShortDto(Author author);

    AuthorDto toDto(Author entity);

    Author toEntity(CreateAuthorRequest request);

    Author update(@MappingTarget Author toUpdate, UpdateAuthorRequest request);
}
