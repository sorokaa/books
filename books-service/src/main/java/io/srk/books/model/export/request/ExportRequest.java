package io.srk.books.model.export.request;

import io.srk.books.model.export.enumeration.ExportType;
import lombok.Data;

import java.util.Map;

@Data
public class ExportRequest {

    private ExportType type;

    private Map<String, Object> data;
}
