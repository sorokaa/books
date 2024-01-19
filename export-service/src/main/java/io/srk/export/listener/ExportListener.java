package io.srk.export.listener;

import io.srk.export.model.remote.request.ExportRemoteRequest;

public interface ExportListener {

    void exportRequestListener(ExportRemoteRequest request);
}
