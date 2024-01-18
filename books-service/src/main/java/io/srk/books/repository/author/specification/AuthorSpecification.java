package io.srk.books.repository.author.specification;

import io.srk.books.model.author.entity.Author;
import io.srk.books.model.author.request.AuthorFilter;
import io.srk.books.repository.CommonSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthorSpecification extends CommonSpecification<Author> {

    public Specification<Author> byFilter(AuthorFilter filter) {
        if (Objects.isNull(filter)) {
            filter = new AuthorFilter();
        }
        return Specification.where(byName(filter.getName()));
    }

    private Specification<Author> byName(String name) {
        return byTextLike(Author.Fields.name, name);
    }
}
