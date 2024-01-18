package io.srk.books.service.book;

import io.srk.books.model.book.dto.BookDto;
import io.srk.books.model.book.dto.BookShortDto;
import io.srk.books.model.book.request.BookFilter;
import io.srk.books.model.book.request.CreateBookRequest;
import io.srk.books.model.book.request.UpdateBookRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    List<BookShortDto> getAll();

    Page<BookShortDto> getByFilter(BookFilter filter, Pageable pageable);

    BookDto getById(Long id);

    BookDto create(CreateBookRequest request);

    BookDto update(Long id, UpdateBookRequest request);

    void delete(Long id);
}
