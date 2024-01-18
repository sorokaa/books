package io.srk.books.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "dictionary-client", url = "${client.dictionary-service-url}/api/dictionaries")
public interface DictionaryClient {

    @GetMapping("/languages/{id}/exists")
    boolean isLanguageExist(@PathVariable Long id);

    @GetMapping("/categories/{id}/exists")
    boolean isCategoryExist(@PathVariable Long id);
}
