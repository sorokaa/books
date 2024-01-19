package io.srk.file.service;

import io.srk.file.model.dto.FileDto;
import io.srk.file.model.dto.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    FileResponse getFileById(Long id);

    FileDto create(MultipartFile file);

    List<FileDto> getAll();
}
