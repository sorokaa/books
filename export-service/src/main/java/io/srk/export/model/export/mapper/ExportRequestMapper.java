package io.srk.export.model.export.mapper;

import io.srk.export.model.export.dto.BooksStatisticExportRequestDto;
import io.srk.export.model.export.dto.ExportRequestDataDto;
import io.srk.export.model.export.dto.ExportRequestDto;
import io.srk.export.model.export.entity.ExportRequest;
import io.srk.export.model.remote.request.ExportRemoteRequest;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ExportRequestMapper {

    ExportRequest toEntity(ExportRemoteRequest request);

    ExportRequestDto toDto(ExportRequest request);

    BooksStatisticExportRequestDto toBookRequest(ExportRequestDto request);

    ExportRequestDataDto toDataDto(ExportRequest request);
}
