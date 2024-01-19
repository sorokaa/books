package io.srk.file.model.mapper;

import io.srk.file.model.dto.FileDto;
import io.srk.file.model.entity.FileEntity;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface FileMapper {

    FileDto toDto(FileEntity saved);
}
