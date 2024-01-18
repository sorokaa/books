package io.srk.books.service.author.impl;

import io.srk.books.exception.EntityNotFoundException;
import io.srk.books.model.author.dto.AuthorDto;
import io.srk.books.model.author.dto.AuthorShortDto;
import io.srk.books.model.author.entity.Author;
import io.srk.books.model.author.mapper.AuthorMapper;
import io.srk.books.model.author.request.AuthorFilter;
import io.srk.books.model.author.request.CreateAuthorRequest;
import io.srk.books.model.author.request.UpdateAuthorRequest;
import io.srk.books.repository.author.AuthorRepository;
import io.srk.books.repository.author.specification.AuthorSpecification;
import io.srk.books.service.author.AuthorService;
import io.srk.books.util.EntityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;
    private final AuthorSpecification authorSpecification;

    @Override
    public List<AuthorShortDto> getAll() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toShortDto)
                .toList();
    }

    @Override
    public Page<AuthorShortDto> getByFilter(AuthorFilter filter, Pageable pageable) {
        Specification<Author> specification = authorSpecification.byFilter(filter);
        return authorRepository.findAll(specification, pageable).map(authorMapper::toShortDto);
    }

    @Override
    public AuthorDto getById(Long id) {
        return authorMapper.toDto(getEntity(id));
    }

    @Override
    public AuthorDto create(CreateAuthorRequest request) {
        Author author = authorMapper.toEntity(request);
        Author saved = authorRepository.save(author);
        return authorMapper.toDto(saved);
    }

    @Override
    public AuthorDto update(Long id, UpdateAuthorRequest request) {
        Author toUpdate = getEntity(id);
        Author updated = authorMapper.update(toUpdate, request);
        Author saved = authorRepository.save(updated);
        return authorMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public List<Author> getAuthorsByIds(List<Long> ids) {
        return authorRepository.findAllById(ids);
    }

    @Override
    public boolean existsById(Long id) {
        return authorRepository.existsById(id);
    }

    private Author getEntity(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityConstants.AUTHOR, id));
    }
}
