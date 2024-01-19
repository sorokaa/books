package io.srk.books.controller;

import io.srk.books.model.author.dto.AuthorDto;
import io.srk.books.model.author.dto.AuthorShortDto;
import io.srk.books.model.author.request.AuthorFilter;
import io.srk.books.model.author.request.CreateAuthorRequest;
import io.srk.books.model.author.request.UpdateAuthorRequest;
import io.srk.books.service.author.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@Tag(name = "Author API")
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Get all authors")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @GetMapping
    public List<AuthorShortDto> getAll() {
        return authorService.getAll();
    }

    @Operation(summary = "Get authors by filter")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @GetMapping("/filter")
    public Page<AuthorShortDto> getByFilter(
            @PageableDefault Pageable pageable,
            @RequestBody AuthorFilter filter
    ) {
        return authorService.getByFilter(filter, pageable);
    }

    @Operation(summary = "Get author by id")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @GetMapping("/{id}")
    public AuthorDto getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @Operation(summary = "Create author")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping
    public AuthorDto create(@RequestBody CreateAuthorRequest request) {
        return authorService.create(request);
    }

    @Operation(summary = "Update author")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PutMapping("/{id}")
    public AuthorDto update(@PathVariable Long id, @RequestBody UpdateAuthorRequest request) {
        return authorService.update(id, request);
    }

    @Operation(summary = "Delete author")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.delete(id);
    }
}
