package com.consume.consumeserver.service;

import com.consume.consumeserver.provider.KafkaConsumerProvider;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class KafkaConsumerServiceImpl implements KafkaConsumerService{
    private final KafkaConsumerProvider kafkaConsumerProvider;

    @Override
    public ConsumerRecords<String, String> getData() {
        ConsumerRecords<String, String> records = kafkaConsumerProvider.getConsumer().poll(Duration.ofSeconds(30));
        return records.isEmpty() ? null : records;
    }

    @Override
    public void commit(){
        kafkaConsumerProvider.getConsumer().commitSync();
    }
}
