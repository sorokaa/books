package io.srk.books.model.author.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AuthorDto extends AuthorShortDto {

    private String biography;
}
