package io.srk.dictionary.service.category.impl;

import io.srk.dictionary.model.dictionary.DictionaryDto;
import io.srk.dictionary.repository.DictionaryRepository;
import io.srk.dictionary.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Qualifier("categoryRepository")
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

    @Override
    public List<DictionaryDto> getByIds(Set<Long> ids) {
        return dictionaryRepository.getByIds(ids);
    }
}
