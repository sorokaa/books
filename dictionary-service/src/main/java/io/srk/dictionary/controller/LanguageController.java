package io.srk.dictionary.controller;

import io.srk.dictionary.model.language.dto.LanguageDto;
import io.srk.dictionary.service.language.LanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/dictionaries/languages")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping
    public List<LanguageDto> getLanguages() {
        return languageService.getAll();
    }

    @GetMapping("/{id}")
    public LanguageDto getById(@PathVariable Long id) {
        return languageService.getById(id);
    }

    @GetMapping("/{id}/exists")
    public boolean existsById(@PathVariable Long id) {
        return languageService.existsById(id);
    }
}
