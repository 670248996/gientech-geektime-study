package com.gientech.iot.kafkaWaiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import java.util.UUID;

@SpringBootApplication
@EnableBinding(Barista.class)
public class KafkaWaiterApplication implements ApplicationRunner {

    @Autowired
    private KafkaWaiterService producer;

    public static void main(String[] args) {
        SpringApplication.run(KafkaWaiterApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        producer.sendMessage("新的咖啡订单" + UUID.randomUUID());
    }
}
