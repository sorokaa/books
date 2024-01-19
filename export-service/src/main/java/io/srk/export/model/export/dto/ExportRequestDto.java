package io.srk.export.model.export.dto;

import io.srk.export.model.export.enumeration.ExportType;
import lombok.Data;

import java.util.Map;

@Data
public class ExportRequestDto {

    private Long id;

    private ExportType type;

    private Map<String, Object> data;
}
