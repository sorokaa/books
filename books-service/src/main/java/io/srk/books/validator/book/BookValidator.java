package io.srk.books.validator.book;

import io.srk.books.client.DictionaryServiceClient;
import io.srk.books.exception.EntityNotFoundException;
import io.srk.books.model.book.request.CreateBookRequest;
import io.srk.books.model.book.request.UpdateBookRequest;
import io.srk.books.service.author.AuthorService;
import io.srk.books.service.publisher.PublisherService;
import io.srk.books.util.EntityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final DictionaryServiceClient dictionaryClient;
    private final PublisherService publisherService;
    private final AuthorService authorService;

    public void validateCreate(CreateBookRequest request) {
        validateLanguage(request.getLanguageId());
        validateCategories(request.getCategoryIds());
        validatePublisher(request.getPublisherId());
        validateAuthors(request.getAuthorIds());
    }

    public void validateUpdate(UpdateBookRequest request) {
        validateLanguage(request.getLanguageId());
        validateCategories(request.getCategoryIds());
        validatePublisher(request.getPublisherId());
        validateAuthors(request.getAuthorIds());
    }

    private void validateLanguage(Long languageId) {
        if (!dictionaryClient.isLanguageExist(languageId)) {
            throw new EntityNotFoundException(EntityConstants.LANGUAGE, languageId);
        }
    }

    private void validateCategories(List<Long> categoryIds) {
        for (Long categoryId : categoryIds) {
            boolean exists = dictionaryClient.isCategoryExist(categoryId);
            if (!exists) {
                throw new EntityNotFoundException(EntityConstants.CATEGORY, categoryId);
            }
        }
    }

    private void validatePublisher(Long publisherId) {
        if (!publisherService.existsById(publisherId)) {
            throw new EntityNotFoundException(EntityConstants.PUBLISHER, publisherId);
        }
    }

    private void validateAuthors(List<Long> authorIds) {
        for (Long authorId : authorIds) {
            if (!authorService.existsById(authorId)) {
                throw new EntityNotFoundException(EntityConstants.AUTHOR, authorId);
            }
        }
    }
}
