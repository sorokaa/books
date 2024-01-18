package io.srk.books.service.publisher.impl;

import io.srk.books.exception.EntityNotFoundException;
import io.srk.books.model.publisher.dto.PublisherDto;
import io.srk.books.model.publisher.dto.PublisherShortDto;
import io.srk.books.model.publisher.entity.Publisher;
import io.srk.books.model.publisher.mapper.PublisherMapper;
import io.srk.books.model.publisher.request.CreatePublisherRequest;
import io.srk.books.model.publisher.request.PublisherFilter;
import io.srk.books.model.publisher.request.UpdatePublisherRequest;
import io.srk.books.repository.publisher.PublisherRepository;
import io.srk.books.repository.publisher.specification.PublisherSpecification;
import io.srk.books.service.publisher.PublisherService;
import io.srk.books.util.EntityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;
    private final PublisherSpecification publisherSpecification;

    @Override
    public List<PublisherShortDto> getAll() {
        return publisherRepository.findAll().stream()
                .map(publisherMapper::toShortDto)
                .toList();
    }

    @Override
    public Page<PublisherShortDto> getByFilter(PublisherFilter filter, Pageable pageable) {
        Specification<Publisher> specification = publisherSpecification.byFilter(filter);
        return publisherRepository.findAll(specification, pageable).map(publisherMapper::toShortDto);
    }

    @Override
    public PublisherDto create(CreatePublisherRequest request) {
        Publisher publisher = publisherMapper.toEntity(request);
        Publisher saved = publisherRepository.save(publisher);
        return publisherMapper.toDto(saved);
    }

    @Override
    public PublisherDto update(Long id, UpdatePublisherRequest request) {
        Publisher toUpdate = getEntity(id);
        Publisher updated = publisherMapper.update(toUpdate, request);
        Publisher saved = publisherRepository.save(updated);
        return publisherMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }

    @Override
    public PublisherDto getById(Long id) {
        return null;
    }

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
