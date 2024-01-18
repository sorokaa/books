package io.srk.dictionary.repository.language;

import io.srk.dictionary.model.language.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LanguageRepository extends JpaRepository<Language, Long> {
}
