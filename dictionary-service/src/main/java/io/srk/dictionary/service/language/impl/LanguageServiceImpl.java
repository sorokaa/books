package io.srk.dictionary.service.language.impl;

import io.srk.dictionary.model.dictionary.DictionaryDto;
import io.srk.dictionary.repository.DictionaryRepository;
import io.srk.dictionary.service.language.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {

    @Qualifier("languageRepository")
    private final DictionaryRepository dictionaryRepository;

    @Override
    public List<DictionaryDto> getAll(String name, Long limit) {
        return dictionaryRepository.findAll(name, limit);
    }

    @Override
    public DictionaryDto getById(Long id) {
        return dictionaryRepository.getById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return dictionaryRepository.existsById(id);
    }
}
