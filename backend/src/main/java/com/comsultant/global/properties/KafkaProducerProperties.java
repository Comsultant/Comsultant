package com.comsultant.global.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "spring.kafka.producer")
public class KafkaProducerProperties {
    private final String bootstrapServers;
    private final String keySerializer;
    private final String valueSerializer;
}
