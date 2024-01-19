package io.srk.books.service.book.impl;

import io.srk.books.exception.EntityNotFoundException;
import io.srk.books.model.book.dto.BookDto;
import io.srk.books.model.book.dto.BookShortDto;
import io.srk.books.model.book.entity.Book;
import io.srk.books.model.book.mapper.BookMapper;
import io.srk.books.model.book.request.AssignBookEntitiesRequest;
import io.srk.books.model.book.request.BookFilter;
import io.srk.books.model.book.request.CreateBookRequest;
import io.srk.books.model.book.request.UpdateBookRequest;
import io.srk.books.model.export.enumeration.ExportType;
import io.srk.books.model.export.request.ExportRequest;
import io.srk.books.producer.ExportRequestProducer;
import io.srk.books.repository.book.BookRepository;
import io.srk.books.repository.book.specification.BookSpecification;
import io.srk.books.service.author.AuthorService;
import io.srk.books.service.book.BookService;
import io.srk.books.service.publisher.PublisherService;
import io.srk.books.util.EntityConstants;
import io.srk.books.validator.book.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookSpecification bookSpecification;
    private final BookValidator bookValidator;
    private final AuthorService authorService;
    private final PublisherService publisherService;
    private final ExportRequestProducer exportRequestProducer;

    @Override
    @Transactional(readOnly = true)
    public List<BookShortDto> getAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toShortDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BookShortDto> getByFilter(BookFilter filter, Pageable pageable) {
        Specification<Book> specification = bookSpecification.byFilter(filter);
        return bookRepository.findAll(specification, pageable).map(bookMapper::toShortDto);
    }

    @Override
    @Transactional(readOnly = true)
    public BookDto getById(Long id) {
        return bookMapper.toDto(getEntity(id));
    }

    @Override
    @Transactional
    public BookDto create(CreateBookRequest request) {
        bookValidator.validateCreate(request);
        Book entity = bookMapper.toEntity(request);
        assignEntities(request, entity);
        Book saved = bookRepository.save(entity);
        return bookMapper.toDto(saved);
    }

    @Override
    @Transactional
    public BookDto update(Long id, UpdateBookRequest request) {
        bookValidator.validateUpdate(request);
        Book toUpdate = getEntity(id);
        Book updated = bookMapper.update(toUpdate, request);
        assignEntities(request, updated);
        Book saved = bookRepository.save(updated);
        return bookMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void exportStatistic() {
        ExportRequest request = new ExportRequest();
        request.setType(ExportType.BOOK_SELL_STATISTIC);
        exportRequestProducer.sendRequest(request);
    }

    private Book getEntity(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityConstants.BOOK, id));
    }

    private void assignEntities(AssignBookEntitiesRequest request, Book entity) {
        entity.setAuthors(authorService.getAuthorsByIds(request.getAuthorIds()));
        entity.setPublisher(publisherService.getPublisherById(request.getPublisherId()));
    }
}
