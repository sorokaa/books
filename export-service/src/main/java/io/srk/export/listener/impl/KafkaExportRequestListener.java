package io.srk.export.listener.impl;

import io.srk.export.configuration.kafka.KafkaTopics;
import io.srk.export.listener.ExportListener;
import io.srk.export.model.remote.request.ExportRemoteRequest;
import io.srk.export.service.ExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaExportRequestListener implements ExportListener {

    private final ExportService exportService;

    @Override
    @KafkaListener(
            topics = KafkaTopics.EXPORT_REQUEST_TOPIC,
            groupId = "export-group-id",
            containerFactory = "kafkaListener"
    )
    public void exportRequestListener(final @Payload ExportRemoteRequest request) {
        exportService.create(request);
    }
}
