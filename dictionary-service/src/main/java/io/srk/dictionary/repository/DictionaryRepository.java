package io.srk.dictionary.repository;

import io.srk.dictionary.exception.EntityNotFoundException;
import io.srk.dictionary.model.dictionary.DictionaryDto;
import io.srk.dictionary.repository.mapper.DictionaryRowMapper;
import io.srk.dictionary.util.EntityConstants;
import io.srk.dictionary.util.SelectSqlBuilderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class DictionaryRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<DictionaryDto> findAll(String name, Long limit) {
        String sql = new SelectSqlBuilderUtil(getTableName())
                .whereLike("value", name)
                .limit(limit)
                .build();
        return jdbcTemplate.query(sql, new DictionaryRowMapper());
    }

    public DictionaryDto getById(Long id) {
        String sql = "SELECT * FROM %s WHERE id = %d".formatted(getTableName(), id);
        try {
            return jdbcTemplate.queryForObject(sql, new DictionaryRowMapper());
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(EntityConstants.findByName(getTableName()), id);
        }
    }

    public boolean existsById(Long id) {
        String sql = "SELECT EXISTS(SELECT * FROM %s WHERE id = %d)".formatted(getTableName(), id);
        return Boolean.TRUE.equals(jdbcTemplate.queryForObject(sql, Boolean.class));
    }

    public List<DictionaryDto> getByIds(Set<Long> ids) {
        String formattedIds = "(" + ids.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",")) + ")";
        String sql = "SELECT * FROM %s WHERE id IN %s".formatted(getTableName(), formattedIds);
        return jdbcTemplate.query(sql, new DictionaryRowMapper());
    }

    public abstract String getTableName();
}
