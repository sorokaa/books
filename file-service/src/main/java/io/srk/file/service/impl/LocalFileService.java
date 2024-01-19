package io.srk.file.service.impl;

import io.srk.file.exception.EntityNotFoundException;
import io.srk.file.exception.ServerInternalException;
import io.srk.file.model.dto.FileDto;
import io.srk.file.model.entity.FileEntity;
import io.srk.file.model.mapper.FileMapper;
import io.srk.file.repository.FileRepository;
import io.srk.file.service.FileService;
import io.srk.file.util.EntityConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalFileService implements FileService {

    @Value("${storage.local.path}")
    private String filePath;

    private final FileRepository fileRepository;
    private final FileMapper fileMapper;

    @Override
    public byte[] getFileById(Long id) {
        FileEntity entity = getEntity(id);
        String fileLocation = entity.getFullPath();
        Path path = Paths.get(fileLocation);
        if (!Files.exists(path)) {
            throw new EntityNotFoundException(EntityConstants.FILE, id);
        }
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            throw new ServerInternalException();
        }
    }

    @Override
    @Transactional
    public FileDto create(MultipartFile file) {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());

        FileEntity entity = new FileEntity();
        entity.setExtension(extension);
        entity.setPath(filePath);
        entity.setName(extractFileName(file.getOriginalFilename()));
        entity.setSize(file.getSize());

        saveFile(file, entity);

        FileEntity saved = fileRepository.save(entity);
        return fileMapper.toDto(saved);
    }

    @Override
    public List<FileDto> getAll() {
        return fileRepository.findAll().stream()
                .map(fileMapper::toDto)
                .toList();
    }

    private void saveFile(MultipartFile file, FileEntity entity) {
        try {
            file.transferTo(new File(entity.getFullPath()));
        } catch (IOException e) {
            throw new ServerInternalException();
        }
    }

    private FileEntity getEntity(Long id) {
        return fileRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityConstants.FILE, id));
    }

    private String extractFileName(String fileName) {
        if (fileName == null) {
            return UUID.randomUUID().toString();
        }
        return fileName.substring(0, fileName.indexOf(".")) + "-" + UUID.randomUUID();
    }
}
