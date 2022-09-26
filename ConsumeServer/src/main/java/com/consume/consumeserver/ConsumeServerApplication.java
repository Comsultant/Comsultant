package com.consume.consumeserver;

import com.consume.consumeserver.service.KafkaConsumerService;
import com.consume.consumeserver.service.KafkaConsumerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ConsumeServerApplication {

	public static void main(String[] args) {
		ApplicationContext applicationContext = SpringApplication.run(ConsumeServerApplication.class, args);
		KafkaConsumerService kafkaConsumerService = applicationContext.getBean(KafkaConsumerServiceImpl.class);

		Queue<ConsumerRecord<String, String>> q = new LinkedList<>();
		while(true){
			ConsumerRecords<String, String> records = kafkaConsumerService.getData();
			if(records == null)
				continue;

			for(ConsumerRecord<String, String> record : records)
				q.offer(record);

			if(q.size() >= 10) {
				StringBuilder sb = new StringBuilder();
				while(!q.isEmpty())
					sb.append(q.poll().value() + "\n");
				System.out.println(sb.toString());
				kafkaConsumerService.commit();
			}
		}
	}
}