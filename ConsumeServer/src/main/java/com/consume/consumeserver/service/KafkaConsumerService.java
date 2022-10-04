package com.consume.consumeserver.service;

import org.apache.kafka.clients.consumer.ConsumerRecords;

public interface KafkaConsumerService {
    ConsumerRecords<String, String> getData();
    void commit();
}
