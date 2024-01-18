package io.srk.books.model.publisher.mapper;

import io.srk.books.model.publisher.dto.PublisherDto;
import io.srk.books.model.publisher.dto.PublisherShortDto;
import io.srk.books.model.publisher.entity.Publisher;
import io.srk.books.model.publisher.request.CreatePublisherRequest;
import io.srk.books.model.publisher.request.UpdatePublisherRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface PublisherMapper {

    @ShortPublisherInfo
    PublisherShortDto toShortDto(Publisher publisher);

    Publisher toEntity(CreatePublisherRequest request);

    PublisherDto toDto(Publisher publisher);

    Publisher update(@MappingTarget Publisher toUpdate, UpdatePublisherRequest request);
}
