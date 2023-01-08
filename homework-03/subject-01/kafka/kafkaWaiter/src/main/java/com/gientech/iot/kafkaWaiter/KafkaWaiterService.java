package com.gientech.iot.kafkaWaiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @description: Kafka服务员服务
 * @author: 王强
 * @dateTime: 2023-01-08 10:59:52
 */
@Slf4j
@Service
public class KafkaWaiterService {
    @Autowired
    private Barista barista;

    public void sendMessage(String message) {
        try {
            Message<String> msg = MessageBuilder.withPayload(message).build();
            barista.outputChannel().send(msg);
            log.info("Kafka服务员: 订单发送成功");
        } catch (Exception e) {
            throw new RuntimeException("Kafka服务员: 订单发送失败");
        }
    }

    @StreamListener(Barista.INPUT_CHANNEL)
    public void receiver(String message) {
        log.info("Kafka服务员接收到消息: " + message);
    }
}
