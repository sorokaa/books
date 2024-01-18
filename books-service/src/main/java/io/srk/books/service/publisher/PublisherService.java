package io.srk.books.service.publisher;

import io.srk.books.model.publisher.dto.PublisherDto;
import io.srk.books.model.publisher.dto.PublisherShortDto;
import io.srk.books.model.publisher.entity.Publisher;
import io.srk.books.model.publisher.request.CreatePublisherRequest;
import io.srk.books.model.publisher.request.PublisherFilter;
import io.srk.books.model.publisher.request.UpdatePublisherRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PublisherService {

    List<PublisherShortDto> getAll();

    Page<PublisherShortDto> getByFilter(PublisherFilter filter, Pageable pageable);

    PublisherDto create(CreatePublisherRequest request);

    PublisherDto update(Long id, UpdatePublisherRequest request);

    void delete(Long id);

    PublisherDto getById(Long id);

    Publisher getPublisherById(Long id);

    boolean existsById(Long id);
}
