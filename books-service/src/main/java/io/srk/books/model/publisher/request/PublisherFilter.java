package io.srk.books.model.publisher.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "Publisher search filter")
public class PublisherFilter {

    @Schema(name = "Publisher name")
    private String name;
}
