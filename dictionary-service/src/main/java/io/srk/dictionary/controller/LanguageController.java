package io.srk.dictionary.controller;

import io.srk.dictionary.model.language.dto.LanguageDto;
import io.srk.dictionary.service.language.LanguageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dictionaries/languages")
@RequiredArgsConstructor
@Tag(name = "Category API")
public class LanguageController {

    private final LanguageService languageService;

    @Operation(summary = "Get all languages")
    @GetMapping
    public List<LanguageDto> getLanguages() {
        return languageService.getAll();
    }

    @Operation(summary = "Get language by id")
    @GetMapping("/{id}")
    public LanguageDto getById(@PathVariable Long id) {
        return languageService.getById(id);
    }

    @Operation(summary = "Get language existence by id")
    @GetMapping("/{id}/exists")
    public boolean existsById(@PathVariable Long id) {
        return languageService.existsById(id);
    }
}
