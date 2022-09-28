package com.comsultant.infra.kafka;

public interface KafkaProducerService {
    boolean sendMessage(String topic, String message);
}
