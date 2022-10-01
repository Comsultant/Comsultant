package com.consume.consumeserver.service;


import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class KafkaScheduler {
    private final HdfsSaverService hdfsSaverService;
    private final KafkaConsumerService kafkaConsumerService;
    private StringBuffer sb = new StringBuffer();

    @Scheduled(fixedRate = 30000)
    public void runHdfs(){
        if(sb.length() == 0)
            return;

        if(hdfsSaverService.save(sb.toString())){
            kafkaConsumerService.commit();
            sb = new StringBuffer();
            System.out.println("success : save data");
        } else
            System.out.println("fail : save data");
    }

    @Scheduled(fixedDelay = 100)
    public void runConsumer(){
        ConsumerRecords<String, String> records = kafkaConsumerService.getData();
        if(records == null)
            return;

        for(ConsumerRecord<String, String> record : records) {
            if(record == null)
                System.out.println("record null");
            System.out.println(record.value());
            sb.append(record.value() + "\n");
        }
    }
}
