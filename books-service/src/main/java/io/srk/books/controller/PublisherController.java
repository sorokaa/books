package io.srk.books.controller;

import io.srk.books.model.publisher.dto.PublisherDto;
import io.srk.books.model.publisher.dto.PublisherShortDto;
import io.srk.books.model.publisher.request.CreatePublisherRequest;
import io.srk.books.model.publisher.request.PublisherFilter;
import io.srk.books.model.publisher.request.UpdatePublisherRequest;
import io.srk.books.service.publisher.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/publishers")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherService publisherService;

    @GetMapping
    public List<PublisherShortDto> getAll() {
        return publisherService.getAll();
    }

    @GetMapping("/filter")
    public Page<PublisherShortDto> getByFiler(
            @PageableDefault Pageable pageable,
            @RequestBody PublisherFilter filter
    ) {
        return publisherService.getByFilter(filter, pageable);
    }

    @GetMapping("/{id}")
    public PublisherDto getById(@PathVariable Long id) {
        return publisherService.getById(id);
    }

    @PostMapping
    public PublisherDto create(@RequestBody CreatePublisherRequest request) {
        return publisherService.create(request);
    }

    @PutMapping("/{id}")
    public PublisherDto update(@PathVariable Long id, @RequestBody UpdatePublisherRequest request) {
        return publisherService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        publisherService.delete(id);
    }
}
