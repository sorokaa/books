package io.srk.books.model.publisher;

import io.srk.books.model.publisher.dto.PublisherShortDto;
import io.srk.books.model.publisher.entity.Publisher;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PublisherMapper {

    PublisherShortDto toShortDto(Publisher publisher);
}
