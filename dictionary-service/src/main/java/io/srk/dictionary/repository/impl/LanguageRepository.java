package io.srk.dictionary.repository.impl;

import io.srk.dictionary.repository.DictionaryRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LanguageRepository extends DictionaryRepository {

    public LanguageRepository(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate);
    }

    @Override
    public String getTableName() {
        return "language";
    }
}
