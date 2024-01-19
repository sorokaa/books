package io.srk.export.configuration.kafka;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class KafkaTopics {

    public static final String EXPORT_REQUEST_TOPIC = "export-request-topic";
}
