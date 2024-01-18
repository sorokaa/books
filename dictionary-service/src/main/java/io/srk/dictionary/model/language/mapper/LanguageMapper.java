package io.srk.dictionary.model.language.mapper;

import io.srk.dictionary.model.language.entity.Language;
import io.srk.dictionary.model.language.dto.LanguageDto;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface LanguageMapper {

    LanguageDto toDto(Language language);
}
