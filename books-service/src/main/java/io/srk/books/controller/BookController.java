package io.srk.books.controller;

import io.srk.books.model.book.dto.BookDto;
import io.srk.books.model.book.dto.BookShortDto;
import io.srk.books.model.book.request.BookFilter;
import io.srk.books.model.book.request.CreateBookRequest;
import io.srk.books.model.book.request.UpdateBookRequest;
import io.srk.books.service.book.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public List<BookShortDto> getAll() {
        return bookService.getAll();
    }

    @PostMapping("/filter")
    public Page<BookShortDto> getByFilter(
            @PageableDefault Pageable pageable,
            @RequestBody BookFilter filter
    ) {
        return bookService.getByFilter(filter, pageable);
    }

    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @PostMapping
    public BookDto create(@Valid @RequestBody CreateBookRequest request) {
        return bookService.create(request);
    }

    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody UpdateBookRequest request) {
        return bookService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
