package io.srk.dictionary.service.category;

import io.srk.dictionary.model.dictionary.DictionaryDto;

import java.util.List;
import java.util.Set;

public interface CategoryService {

    List<DictionaryDto> getAll(String name, Long limit);

    DictionaryDto getById(Long id);

    boolean existsById(Long id);

    List<DictionaryDto> getByIds(Set<Long> ids);
}
