package io.srk.export.helper;

import io.srk.export.model.export.dto.ExportRequestDto;

public interface ExportHelper<T extends ExportRequestDto> {

    byte[] export(T request);
}
