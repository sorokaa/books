package io.srk.file.service;

import io.srk.file.model.dto.FileDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileService {

    byte[] getFileById(Long id);

    FileDto create(MultipartFile file);

    List<FileDto> getAll();
}
