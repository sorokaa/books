package io.srk.books.model.author.entity;

import io.srk.books.model.book.entity.Book;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.FieldNameConstants;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@FieldNameConstants
@Table(schema = "book_schema", name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String biography;

    @ManyToMany(mappedBy = "authors")
    private List<Book> books = new ArrayList<>();
}
