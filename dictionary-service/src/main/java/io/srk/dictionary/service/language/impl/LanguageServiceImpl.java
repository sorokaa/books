package io.srk.dictionary.service.language.impl;

import io.srk.dictionary.exception.EntityNotFoundException;
import io.srk.dictionary.model.language.dto.LanguageDto;
import io.srk.dictionary.model.language.entity.Language;
import io.srk.dictionary.model.language.mapper.LanguageMapper;
import io.srk.dictionary.repository.language.LanguageRepository;
import io.srk.dictionary.service.language.LanguageService;
import io.srk.dictionary.util.EntityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;
    private final LanguageMapper languageMapper;

    @Override
    public List<LanguageDto> getAll() {
        return languageRepository.findAll().stream()
                .map(languageMapper::toDto)
                .toList();
    }

    @Override
    public LanguageDto getById(Long id) {
        return languageMapper.toDto(getEntity(id));
    }

    @Override
    public boolean existsById(Long id) {
        return languageRepository.existsById(id);
    }

    private Language getEntity(Long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityConstants.LANGUAGE, id));
    }
}
