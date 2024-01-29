package io.srk.dictionary.repository.mapper;

import io.srk.dictionary.model.dictionary.DictionaryDto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DictionaryRowMapper implements RowMapper<DictionaryDto> {

    @Override
    public DictionaryDto mapRow(ResultSet rs, int rowNum) throws SQLException {
        DictionaryDto dictionary = new DictionaryDto();
        dictionary.setId(rs.getLong("id"));
        dictionary.setValue(rs.getString("value"));
        return dictionary;
    }
}
