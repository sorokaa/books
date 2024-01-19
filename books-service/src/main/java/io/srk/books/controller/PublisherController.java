package io.srk.books.controller;

import io.srk.books.model.publisher.dto.PublisherDto;
import io.srk.books.model.publisher.dto.PublisherShortDto;
import io.srk.books.model.publisher.request.CreatePublisherRequest;
import io.srk.books.model.publisher.request.PublisherFilter;
import io.srk.books.model.publisher.request.UpdatePublisherRequest;
import io.srk.books.service.publisher.PublisherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
@Tag(name = "Publisher API")
public class PublisherController {

    private final PublisherService publisherService;

    @Operation(summary = "Get all publishers")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @GetMapping
    public List<PublisherShortDto> getAll() {
        log.debug("API request to get all publishers");
        return publisherService.getAll();
    }

    @Operation(summary = "Get publishers by filter")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @GetMapping("/filter")
    public Page<PublisherShortDto> getByFiler(
            @PageableDefault Pageable pageable,
            @RequestBody PublisherFilter filter
    ) {
        log.debug("API request to get publishers by filter {}", filter);
        return publisherService.getByFilter(filter, pageable);
    }

    @Operation(summary = "Get publisher by id")
    @PreAuthorize("hasAnyAuthority(@authorities.ROLE_ADMIN, @authorities.ROLE_CLIENT)")
    @GetMapping("/{id}")
    public PublisherDto getById(@PathVariable Long id) {
        log.debug("API request to get publisher by id {}", id);
        return publisherService.getById(id);
    }

    @Operation(summary = "Create publisher")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PostMapping
    public PublisherDto create(@RequestBody CreatePublisherRequest request) {
        log.debug("API request to create publisher {}", request);
        return publisherService.create(request);
    }

    @Operation(summary = "Update publisher")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @PutMapping("/{id}")
    public PublisherDto update(@PathVariable Long id, @RequestBody UpdatePublisherRequest request) {
        log.debug("API request to update publisher {}", request);
        return publisherService.update(id, request);
    }

    @Operation(summary = "Delete publisher")
    @PreAuthorize("hasAuthority(@authorities.ROLE_ADMIN)")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        log.debug("API request to delete publisher by id {}", id);
        publisherService.delete(id);
    }
}
