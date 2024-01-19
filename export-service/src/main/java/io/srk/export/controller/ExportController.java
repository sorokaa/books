package io.srk.export.controller;

import io.srk.export.model.export.dto.ExportRequestDataDto;
import io.srk.export.service.ExportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
@Tag(name = "Export API")
public class ExportController {

    private final ExportService exportService;

    @Operation(summary = "Get all export requests")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PageableAsQueryParam
    @GetMapping
    public Page<ExportRequestDataDto> getAll(
            @ParameterObject @PageableDefault Pageable pageable
    ) {
        return exportService.getAll(pageable);
    }
}
