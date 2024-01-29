package io.srk.dictionary.service.language;

import io.srk.dictionary.model.dictionary.DictionaryDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface LanguageService {

    @Transactional(readOnly = true)
    List<DictionaryDto> getAll(String name, Long limit);

    DictionaryDto getById(Long id);

    boolean existsById(Long id);
}
