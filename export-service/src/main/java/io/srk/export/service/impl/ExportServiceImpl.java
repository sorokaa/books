package io.srk.export.service.impl;

import io.srk.export.model.export.dto.ExportRequestDataDto;
import io.srk.export.model.export.entity.ExportRequest;
import io.srk.export.model.export.enumeration.ExportStatus;
import io.srk.export.model.export.mapper.ExportRequestMapper;
import io.srk.export.model.remote.request.ExportRemoteRequest;
import io.srk.export.repository.ExportRequestRepository;
import io.srk.export.service.ExportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExportServiceImpl implements ExportService {

    private final ExportRequestRepository exportRequestRepository;
    private final ExportRequestMapper exportRequestMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<ExportRequestDataDto> getAll(Pageable pageable) {
        return exportRequestRepository.findAll(pageable)
                .map(exportRequestMapper::toDataDto);
    }

    @Transactional
    public void create(ExportRemoteRequest request) {
        ExportRequest entity = exportRequestMapper.toEntity(request);
        entity.setStatus(ExportStatus.NEW);
        exportRequestRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ExportRequest> getRequestToProcess() {
        return exportRequestRepository.getRequestToProcess();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateStatus(Long id, ExportStatus status) {
        exportRequestRepository.updateStatus(id, status);
    }
}
