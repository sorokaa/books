package io.srk.export.service;

import io.srk.export.model.export.dto.ExportRequestDataDto;
import io.srk.export.model.export.entity.ExportRequest;
import io.srk.export.model.export.enumeration.ExportStatus;
import io.srk.export.model.remote.request.ExportRemoteRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ExportService {

    Page<ExportRequestDataDto> getAll(Pageable pageable);

    void create(ExportRemoteRequest request);

    Optional<ExportRequest> getRequestToProcess();

    void updateStatus(Long id, ExportStatus exportStatus);
}
