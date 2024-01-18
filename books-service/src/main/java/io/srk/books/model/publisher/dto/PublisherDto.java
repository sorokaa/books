package io.srk.books.model.publisher.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "Full information about publisher")
public class PublisherDto extends PublisherShortDto {
}
