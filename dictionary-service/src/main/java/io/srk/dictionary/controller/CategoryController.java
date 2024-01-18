package io.srk.dictionary.controller;

import io.srk.dictionary.model.category.dto.CategoryDto;
import io.srk.dictionary.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dictionaries/categories")
@RequiredArgsConstructor
@Tag(name = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @GetMapping
    public List<CategoryDto> getAll() {
        return categoryService.getAll();
    }

    @Operation(summary = "Get category by id")
    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        return categoryService.getById(id);
    }

    @Operation(summary = "Get category existence by id")
    @GetMapping("/{id}/exists")
    public boolean existsById(@PathVariable Long id) {
        return categoryService.existsById(id);
    }
}
