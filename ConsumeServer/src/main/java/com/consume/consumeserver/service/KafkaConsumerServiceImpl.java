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

    // Kafka 응답 시간 1초.
    @Override
    public ConsumerRecords<String, String> getData() {
        ConsumerRecords<String, String> records = kafkaConsumerProvider.getConsumer().poll(Duration.ofSeconds(1));
        return records.isEmpty() ? null : records;
    }

    // offset commit
    @Override
    public void commit(){
        try{
            kafkaConsumerProvider.getConsumer().commitSync();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
