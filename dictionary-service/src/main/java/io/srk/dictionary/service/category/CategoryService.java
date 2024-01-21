package io.srk.dictionary.service.category;

import io.srk.dictionary.model.category.dto.CategoryDto;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<CategoryDto> getAll();

    CategoryDto getById(Long id);

    boolean existsById(Long id);

    List<CategoryDto> getByIds(Set<Long> ids);
}
