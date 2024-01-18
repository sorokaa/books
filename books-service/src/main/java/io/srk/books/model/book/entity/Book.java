package io.srk.books.model.book.entity;

import io.srk.books.model.author.entity.Author;
import io.srk.books.model.book.enumeration.BookStatus;
import io.srk.books.model.publisher.entity.Publisher;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@FieldNameConstants
@Table(schema = "book_schema", name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private BigDecimal price;

    @Column(columnDefinition = "varchar(255)")
    @Enumerated(EnumType.STRING)
    private BookStatus status;

    private Long pages;

    private Short year;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;

    private Long languageId;

    @ElementCollection
    @CollectionTable(
            schema = "book_schema",
            name = "book_category",
            joinColumns = @JoinColumn(referencedColumnName = "id")
    )
    @Column(name = "category_id")
    private List<Long> categoryIds = new ArrayList<>();

    @JoinTable(
            schema = "book_schema",
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @ManyToMany
    private List<Author> authors = new ArrayList<>();
}
