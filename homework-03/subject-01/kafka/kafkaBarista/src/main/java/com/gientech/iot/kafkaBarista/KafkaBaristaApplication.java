package com.gientech.iot.kafkaBarista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(Barista.class)
public class KafkaBaristaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaBaristaApplication.class, args);
    }

}
