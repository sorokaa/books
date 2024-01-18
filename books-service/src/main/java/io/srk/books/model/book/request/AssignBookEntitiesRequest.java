package io.srk.books.model.book.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public abstract class AssignBookEntitiesRequest {

    @NotNull(message = "Publisher cannot be null")
    private Long publisherId;

    @NotEmpty(message = "Authors cannot be empty")
    private List<Long> authorIds = new ArrayList<>();
}
