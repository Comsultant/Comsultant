package com.comsultant.infra.kafka;

import com.comsultant.global.config.kafka.KafkaProducerProvider;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducerServiceImpl implements KafkaProducerService {
    private final KafkaProducerProvider kafkaProducerProvider;

    /**
     * @param topic Kafka 내 저장할 topic
     * @param message 저장할 데이터의 toString()
     * @return 성공 여부
     */
    @Override
    public boolean sendMessage(String topic, String message) {
        try {
            kafkaProducerProvider.getProducer().send(new ProducerRecord<>(topic, message));
            return true;
        } catch(Exception e){
            return false;
        }
    }
}
