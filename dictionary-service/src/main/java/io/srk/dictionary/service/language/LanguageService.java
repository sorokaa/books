package io.srk.dictionary.service.language;

import io.srk.dictionary.model.language.dto.LanguageDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LanguageService {

    @Transactional(readOnly = true)
    List<LanguageDto> getAll();

    LanguageDto getById(Long id);

    boolean existsById(Long id);
}
