package io.srk.export.model.remote.request;

import io.srk.export.model.export.enumeration.ExportType;
import lombok.Data;

import java.util.Map;

@Data
public class ExportRemoteRequest {

    private ExportType type;

    private Map<String, Object> data;
}
