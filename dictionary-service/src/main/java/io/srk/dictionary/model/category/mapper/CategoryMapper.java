package io.srk.dictionary.model.category.mapper;

import io.srk.dictionary.model.category.dto.CategoryDto;
import io.srk.dictionary.model.category.entity.Category;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface CategoryMapper {

    CategoryDto toDto(Category category);
}
