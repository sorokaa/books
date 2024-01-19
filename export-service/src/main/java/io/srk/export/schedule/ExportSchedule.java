package io.srk.export.schedule;

import io.srk.export.exception.ExportProcessingException;
import io.srk.export.exception.ServerInternalException;
import io.srk.export.helper.impl.ExportHelperFacade;
import io.srk.export.model.export.dto.ExportRequestDto;
import io.srk.export.model.export.entity.ExportRequest;
import io.srk.export.model.export.enumeration.ExportStatus;
import io.srk.export.model.export.mapper.ExportRequestMapper;
import io.srk.export.model.file.FileDto;
import io.srk.export.service.ExportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class ExportSchedule {

    private final ExportService exportService;
    private final ExportRequestMapper exportRequestMapper;
    private final ExportHelperFacade exportHelperFacade;

    @Transactional
    @Scheduled(fixedDelayString = "${export.delay}", timeUnit = TimeUnit.SECONDS)
    public void processExport() {
        log.debug("Export process started");
        var requestOpt = exportService.getRequestToProcess();
        if (requestOpt.isEmpty()) {
            log.debug("Noting to export");
            return;
        }
        ExportRequest request = requestOpt.get();
        exportService.updateStatus(request.getId(), ExportStatus.PROCESSING);

        ExportRequestDto requestDto = exportRequestMapper.toDto(request);

        FileDto file = export(requestDto, request);
        request.setStatus(ExportStatus.DONE);
        request.setFileId(file.getId());
    }

    private FileDto export(ExportRequestDto requestDto, ExportRequest request) {
        log.debug("Start exporting");
        try {
            return exportHelperFacade.export(requestDto);
        } catch (ExportProcessingException e) {
            request.setStatus(ExportStatus.FAILED);
            request.setFailureReason(e.getMessage());
            log.error("Error occurred while exporting. Reason: {}", e.getMessage());
            throw new ServerInternalException();
        }
    }
}
