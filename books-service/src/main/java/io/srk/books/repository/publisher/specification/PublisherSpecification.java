package io.srk.books.repository.publisher.specification;

import io.srk.books.model.publisher.entity.Publisher;
import io.srk.books.model.publisher.request.PublisherFilter;
import io.srk.books.repository.CommonSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PublisherSpecification extends CommonSpecification<Publisher> {

    public Specification<Publisher> byFilter(PublisherFilter filter) {
        if (Objects.isNull(filter)) {
            filter = new PublisherFilter();
        }
        return Specification.where(byName(filter.getName()));
    }

    private Specification<Publisher> byName(String name) {
        return byTextLike(Publisher.Fields.name, name);
    }
}
