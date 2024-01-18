package io.srk.books.service.author;

import io.srk.books.model.author.dto.AuthorDto;
import io.srk.books.model.author.dto.AuthorShortDto;
import io.srk.books.model.author.entity.Author;
import io.srk.books.model.author.request.AuthorFilter;
import io.srk.books.model.author.request.CreateAuthorRequest;
import io.srk.books.model.author.request.UpdateAuthorRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AuthorService {

    List<AuthorShortDto> getAll();

    Page<AuthorShortDto> getByFilter(AuthorFilter filter, Pageable pageable);

    AuthorDto getById(Long id);

    AuthorDto create(CreateAuthorRequest request);

    AuthorDto update(Long id, UpdateAuthorRequest request);

    void delete(Long id);

    List<Author> getAuthorsByIds(List<Long> ids);

    boolean existsById(Long id);
}
