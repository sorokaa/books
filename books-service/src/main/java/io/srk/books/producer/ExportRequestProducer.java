package io.srk.books.producer;

import io.srk.books.configuration.kafka.KafkaTopics;
import io.srk.books.model.export.request.ExportRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExportRequestProducer {

    private final KafkaTemplate<String, ExportRequest> kafkaTemplate;

    public void sendRequest(ExportRequest request) {
        kafkaTemplate.send(KafkaTopics.EXPORT_REQUEST_TOPIC, request);
    }
}
