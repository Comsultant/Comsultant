package com.comsultant.global.config.kafka;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Getter
@Component
@RequiredArgsConstructor
public class KafkaProducerProvider {
    private final KafkaProducerConfig kafkaProducerConfig;
    private KafkaProducer<String, String> producer;

    @PostConstruct
    protected void init() {
        producer = new KafkaProducer<String, String>(kafkaProducerConfig.kafkaConfigs());
    }
}
