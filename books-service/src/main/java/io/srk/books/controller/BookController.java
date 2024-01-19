package io.srk.books.controller;

import io.srk.books.model.book.dto.BookDto;
import io.srk.books.model.book.dto.BookShortDto;
import io.srk.books.model.book.dto.BookStatisticDto;
import io.srk.books.model.book.request.BookFilter;
import io.srk.books.model.book.request.CreateBookRequest;
import io.srk.books.model.book.request.UpdateBookRequest;
import io.srk.books.service.statistic.BookStatisticService;
import io.srk.books.service.book.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
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
        return bookService.getAll();
    }

    @Operation(summary = "Get books by filter")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @PostMapping("/filter")
    public Page<BookShortDto> getByFilter(
            @PageableDefault Pageable pageable,
            @RequestBody BookFilter filter
    ) {
        return bookService.getByFilter(filter, pageable);
    }

    @Operation(summary = "Get book by id")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @GetMapping("/{id}")
    public BookDto getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    @Operation(summary = "Create book")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping
    public BookDto create(@Valid @RequestBody CreateBookRequest request) {
        return bookService.create(request);
    }

    @Operation(summary = "Update book")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PutMapping("/{id}")
    public BookDto update(@PathVariable Long id, @RequestBody UpdateBookRequest request) {
        return bookService.update(id, request);
    }

    @Operation(summary = "Delete book")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }

    @Operation(summary = "Export books statistic")
    @PostMapping("/statistic/export")
    public void exportStatistic() {
        bookService.exportStatistic();
    }

    @Operation(summary = "Get books statistic")
    @GetMapping("/statistic")
    public List<BookStatisticDto> getStatistic() {
        return bookStatisticService.getStatistic();
    }
}
