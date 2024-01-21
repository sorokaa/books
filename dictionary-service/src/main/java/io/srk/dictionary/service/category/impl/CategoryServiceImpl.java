package io.srk.dictionary.service.category.impl;

import io.srk.dictionary.exception.EntityNotFoundException;
import io.srk.dictionary.model.category.dto.CategoryDto;
import io.srk.dictionary.model.category.entity.Category;
import io.srk.dictionary.model.category.mapper.CategoryMapper;
import io.srk.dictionary.repository.category.CategoryRepository;
import io.srk.dictionary.service.category.CategoryService;
import io.srk.dictionary.util.EntityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryDto> getAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    @Override
    public CategoryDto getById(Long id) {
        return categoryMapper.toDto(getEntity(id));
    }

    @Override
    public boolean existsById(Long id) {
        return categoryRepository.existsById(id);
    }

    @Override
    public List<CategoryDto> getByIds(Set<Long> ids) {
        return categoryRepository.findAllById(ids).stream()
                .map(categoryMapper::toDto)
                .toList();
    }

    private Category getEntity(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityConstants.CATEGORY, id));
    }
}
