package io.srk.file.controller;

import io.srk.file.model.dto.FileDto;
import io.srk.file.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@Tag(name = "File API")
public class FileController {

    private final FileService fileService;

    @Operation(summary = "Get all files")
    @GetMapping
    public List<FileDto> getAll() {
        log.debug("API request to get all files");
        return fileService.getAll();
    }

    @Operation(summary = "Get file by id")
    @GetMapping("/{id}")
    public byte[] getFileById(@PathVariable Long id) {
        log.debug("API request to get file by id {}", id);
        return fileService.getFileById(id);
    }

    @Operation(summary = "Create file")
    @PostMapping
    public FileDto create(@RequestParam("file") MultipartFile file) {
        log.debug("API request to create file with name {}", file.getOriginalFilename());
        return fileService.create(file);
    }
}
