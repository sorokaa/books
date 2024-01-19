package io.srk.export.model.export.dto;

import io.srk.export.model.export.enumeration.ExportStatus;
import lombok.Data;

import java.time.Instant;

@Data
public class ExportRequestDataDto {

    private Long id;

    private ExportStatus status;

    private Long fileId;

    private Instant created;

    private Instant updated;
}
