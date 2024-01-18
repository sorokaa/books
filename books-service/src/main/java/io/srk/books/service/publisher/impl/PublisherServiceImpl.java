package io.srk.books.service.publisher.impl;

import io.srk.books.exception.EntityNotFoundException;
import io.srk.books.model.publisher.entity.Publisher;
import io.srk.books.repository.publisher.PublisherRepository;
import io.srk.books.service.publisher.PublisherService;
import io.srk.books.util.EntityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;

    @Override
    public Publisher getPublisherById(Long id) {
        return getEntity(id);
    }

    @Override
    public boolean existsById(Long id) {
        return publisherRepository.existsById(id);
    }

    private Publisher getEntity(Long id) {
        return publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(EntityConstants.PUBLISHER, id));
    }
}
