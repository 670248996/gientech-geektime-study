package com.gientech.iot.rabbitWaiter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

import java.util.UUID;

@SpringBootApplication
@EnableBinding(Barista.class)
public class RabbitWaiterApplication implements ApplicationRunner {

    @Autowired
    private RabbitWaiterService producer;

    public static void main(String[] args) {
        SpringApplication.run(RabbitWaiterApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        producer.sendMessage("新的咖啡订单" + UUID.randomUUID());
    }
}
