package io.srk.books.repository.book.specification;

import io.srk.books.model.book.request.BookFilter;
import io.srk.books.model.book.entity.Book;
import io.srk.books.model.book.enumeration.BookStatus;
import io.srk.books.repository.CommonSpecification;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Component
public class BookSpecification extends CommonSpecification<Book> {

    public Specification<Book> byFilter(BookFilter filter) {
        if (Objects.isNull(filter)) {
            filter = new BookFilter();
        }
        return Specification.where(byYearRange(filter.getYearRange()))
                .and(byPriceRange(filter.getPriceRange()))
                .and(byPagesRange(filter.getPagesRange()))
                .and(byName(filter.getName()))
                .and(byStatuses(filter.getStatuses()))
                .and(byPublishers(filter.getPublisherIds()))
                .and(byCategories(filter.getCategoryIds()))
                .and(byLanguages(filter.getLanguageIds()))
                .and(byAuthors(filter.getAuthorIds()));
    }

    private Specification<Book> byAuthors(List<Long> authorIds) {
        return byValueIn(authorIds, Book.Fields.authors, "id");
    }

    private Specification<Book> byLanguages(List<Long> languageIds) {
        return byValueIn(Book.Fields.languageId, languageIds);
    }

    private Specification<Book> byCategories(List<Long> categoryIds) {
       if (CollectionUtils.isEmpty(categoryIds)) {
           return null;
       }
       return (root, query, criteriaBuilder) -> root.join(Book.Fields.categoryIds).in(categoryIds);
    }

    private Specification<Book> byPublishers(List<Long> publisherIds) {
        return byValueIn(publisherIds, Book.Fields.publisher, "id");
    }

    private Specification<Book> byStatuses(List<BookStatus> statuses) {
        return byValueIn(Book.Fields.status, statuses);
    }

    private Specification<Book> byName(String name) {
        return byTextLike(Book.Fields.name, name);
    }

    private Specification<Book> byYearRange(BookFilter.Range<Short> range) {
        return byRange(Book.Fields.year, range);
    }

    private Specification<Book> byPriceRange(BookFilter.Range<BigDecimal> range) {
        return byRange(Book.Fields.price, range);
    }

    private Specification<Book> byPagesRange(BookFilter.Range<Long> range) {
        return byRange(Book.Fields.pages, range);
    }
}
