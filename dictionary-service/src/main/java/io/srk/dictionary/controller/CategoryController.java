package io.srk.dictionary.controller;

import io.srk.dictionary.model.category.dto.CategoryDto;
import io.srk.dictionary.service.category.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/api/dictionaries/categories")
@RequiredArgsConstructor
@Tag(name = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    @Operation(summary = "Get all categories")
    @GetMapping
    public List<CategoryDto> getAll() {
        log.debug("API request to get all categories");
        return categoryService.getAll();
    }

    @Operation(summary = "Get category by id")
    @GetMapping("/{id}")
    public CategoryDto getById(@PathVariable Long id) {
        log.debug("API request to get category by id {}", id);
        return categoryService.getById(id);
    }

    @Operation(summary = "Get categories by ids")
    @GetMapping("/by-ids")
    public List<CategoryDto> getById(@RequestParam Set<Long> ids) {
        log.debug("API request to get categories by ids {}", ids);
        return categoryService.getByIds(ids);
    }

    @Operation(summary = "Get category existence by id")
    @GetMapping("/{id}/exists")
    public boolean existsById(@PathVariable Long id) {
        log.debug("API request to check category existence by id {}", id);
        return categoryService.existsById(id);
    }
}
