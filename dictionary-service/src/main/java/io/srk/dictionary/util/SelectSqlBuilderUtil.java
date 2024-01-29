package io.srk.dictionary.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class SelectSqlBuilderUtil {

    private String sql;

    public SelectSqlBuilderUtil(String tableName) {
        this.sql = "SELECT * FROM %s WHERE 1 = 1".formatted(tableName);
    }

    public String build() {
        return sql;
    }

    public SelectSqlBuilderUtil whereLike(String column, String value) {
        if (StringUtils.isBlank(value)) {
            return this;
        }
        this.sql += " AND %s LIKE '%%%s%%'".formatted(column, value);
        return this;
    }

    public SelectSqlBuilderUtil limit(Long limit) {
        if (Objects.isNull(limit)) {
            return this;
        }
        this.sql += " LIMIT %d ".formatted(limit);
        return this;
    }
}
