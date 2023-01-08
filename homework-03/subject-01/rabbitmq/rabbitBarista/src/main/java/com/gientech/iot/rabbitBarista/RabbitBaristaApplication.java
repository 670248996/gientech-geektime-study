package com.gientech.iot.rabbitBarista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding(Barista.class)
public class RabbitBaristaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RabbitBaristaApplication.class, args);
    }
}
