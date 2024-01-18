package io.srk.books.controller;

import io.srk.books.model.author.dto.AuthorDto;
import io.srk.books.model.author.dto.AuthorShortDto;
import io.srk.books.model.author.request.AuthorFilter;
import io.srk.books.model.author.request.CreateAuthorRequest;
import io.srk.books.model.author.request.UpdateAuthorRequest;
import io.srk.books.service.author.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    public List<AuthorShortDto> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/filter")
    public Page<AuthorShortDto> getByFilter(
            @PageableDefault Pageable pageable,
            @RequestBody AuthorFilter filter
    ) {
        return authorService.getByFilter(filter, pageable);
    }

    @GetMapping("/{id}")
    public AuthorDto getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @PostMapping
    public AuthorDto create(@RequestBody CreateAuthorRequest request) {
        return authorService.create(request);
    }

    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable Long id, @RequestBody UpdateAuthorRequest request) {
        return authorService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
