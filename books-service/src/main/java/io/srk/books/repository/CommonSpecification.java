package io.srk.books.repository;

import io.srk.books.model.book.request.BookFilter;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Objects;

public abstract class CommonSpecification<T> {

    protected <L> Specification<T> byValueIn(String field, List<L> values) {
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        return (root, query, criteriaBuilder) -> root.get(field).in(values);
    }

    protected <L> Specification<T> byValueIn(List<L> values, String... path) {
        if (CollectionUtils.isEmpty(values)) {
            return null;
        }
        return (root, query, criteriaBuilder) -> getNestedRoot(root, path).in(values);
    }

    protected Specification<T> byTextLike(String field, String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get(field), "%" + name + "%");
    }

    protected <L extends Comparable<L>> Specification<T> byRange(String field, BookFilter.Range<L> range) {
        if (range == null || (range.getMinValue() == null && range.getMaxValue() == null)) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            L minValue = range.getMinValue();
            L maxValue = range.getMaxValue();
            if (minValue != null && maxValue != null) {
                return criteriaBuilder.between(root.get(field), minValue, maxValue);
            }
            if (minValue == null) {
                return criteriaBuilder.lessThan(root.get(field), maxValue);
            }
            return criteriaBuilder.greaterThan(root.get(field), minValue);
        };
    }

    private Path<T> getNestedRoot(Root<T> root, String... paths) {
        Path<T> fieldPath = null;
        for (String path : paths) {
            fieldPath = Objects.isNull(fieldPath) ? root.get(path) : fieldPath.get(path);
        }
        return fieldPath;
    }
}
