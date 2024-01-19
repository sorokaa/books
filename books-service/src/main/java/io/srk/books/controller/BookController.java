package io.srk.books.controller;

import io.srk.books.model.book.dto.BookDto;
import io.srk.books.model.book.dto.BookShortDto;
import io.srk.books.model.book.dto.BookStatisticDto;
import io.srk.books.model.book.request.BookFilter;
import io.srk.books.model.book.request.CreateBookRequest;
import io.srk.books.model.book.request.UpdateBookRequest;
import io.srk.books.service.book.BookService;
import io.srk.books.service.statistic.BookStatisticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Book API")
public class BookController {

    private final BookService bookService;
    private final BookStatisticService bookStatisticService;

    @Operation(summary = "Get all books")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @GetMapping
    public List<BookShortDto> getAll() {
        log.debug("API request to get all books");
        return bookService.getAll();
    }

    @Operation(summary = "Get books by filter")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @PostMapping("/filter")
    public Page<BookShortDto> getByFilter(
            @PageableDefault Pageable pageable,
            @RequestBody BookFilter filter
    ) {
        log.debug("API request to get books by filter {}", filter);
        return bookService.getByFilter(filter, pageable);
    }

    @Operation(summary = "Get book by id")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        log.debug("API request to get book by id {}", id);
        return bookService.getById(id);
    }

    @Operation(summary = "Create book")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping
    public BookDto create(@Valid @RequestBody CreateBookRequest request) {
        log.debug("API request to create book. Request: {}", request);
        return bookService.create(request);
    }

    @Operation(summary = "Update book")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody UpdateBookRequest request) {
        log.debug("API request to update book. Request: {}", request);
        return bookService.update(id, request);
    }

    @Operation(summary = "Delete book")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.debug("API request to delete book by id: {}", id);
        bookService.delete(id);
    }

    @Operation(summary = "Export books statistic")
    @PostMapping("/statistic/export")
    public void exportStatistic() {
        log.debug("API request to export books statistic");
        bookService.exportStatistic();
    }

    @Operation(summary = "Get books statistic")
    @GetMapping("/statistic")
    public List<BookStatisticDto> getStatistic() {
        log.debug("API request to get book statistic");
        return bookStatisticService.getStatistic();
    }
}
