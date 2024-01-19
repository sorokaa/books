package io.srk.file.exception;

import io.srk.file.util.EntityConstants;
import lombok.Getter;

@Getter
public class EntityNotFoundException extends ClientException {

    public EntityNotFoundException(EntityConstants entity, Object id) {
        super(ExceptionCode.ENTITY_DOES_NOT_EXIST, entity, id);
    }
}
