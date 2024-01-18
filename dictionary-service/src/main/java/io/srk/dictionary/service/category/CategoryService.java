package io.srk.dictionary.service.category;

import io.srk.dictionary.model.category.dto.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAll();

    CategoryDto getById(Long id);

    boolean existsById(Long id);
}
