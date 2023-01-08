package com.gientech.iot.kafkaBarista;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Service;

/**
 * @description: Kafka咖啡师服务
 * @author: 王强
 * @dateTime: 2023-01-07 20:21:48
 */
@Slf4j
@Service
public class KafkaBaristaService {

    @StreamListener(Barista.INPUT_CHANNEL)
    @SendTo(Barista.OUTPUT_CHANNEL)
    public String receiver(String message) throws InterruptedException {
        log.info("Kafka咖啡师接收到消息: " + message);
        Thread.sleep(5000);
        log.info("Kafka咖啡师: 咖啡制作完成");
        return "咖啡制作完成";
    }
}
