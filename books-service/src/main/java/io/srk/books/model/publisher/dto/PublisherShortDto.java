package io.srk.books.model.publisher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Short info about publisher")
public class PublisherShortDto {

    @Schema(description = "Publisher id")
    private Long id;

    @Schema(description = "Publisher name")
    private String name;
}
