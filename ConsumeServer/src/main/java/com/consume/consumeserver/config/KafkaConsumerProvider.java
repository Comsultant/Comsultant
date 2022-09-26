package com.consume.consumeserver.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Getter
@Component
@RequiredArgsConstructor
public class KafkaConsumerProvider {
    private final KafkaConsumerConfig kafkaConsumerConfig;
    private final String TOPIC_NAME = "test";
    private KafkaConsumer<String, String> consumer;

    @PostConstruct
    protected void init(){
        consumer = new KafkaConsumer<String, String>(kafkaConsumerConfig.kafkaConfigs());
        consumer.subscribe(Arrays.asList(TOPIC_NAME));
    }
}
