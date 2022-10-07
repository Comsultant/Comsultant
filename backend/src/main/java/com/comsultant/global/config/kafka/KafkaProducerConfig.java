package com.comsultant.global.config.kafka;

import com.comsultant.global.properties.KafkaProducerProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class KafkaProducerConfig {
    private final KafkaProducerProperties kafkaProducerProperties;

    @Bean
    public Properties kafkaConfigs(){
        Properties configs = new Properties();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerProperties.getBootstrapServers());
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProducerProperties.getKeySerializer());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProducerProperties.getValueSerializer());
        return configs;
    }
}
