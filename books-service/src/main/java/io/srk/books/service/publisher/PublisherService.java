package io.srk.books.service.publisher;

import io.srk.books.model.publisher.entity.Publisher;

public interface PublisherService {

    Publisher getPublisherById(Long id);

    boolean existsById(Long id);
}
