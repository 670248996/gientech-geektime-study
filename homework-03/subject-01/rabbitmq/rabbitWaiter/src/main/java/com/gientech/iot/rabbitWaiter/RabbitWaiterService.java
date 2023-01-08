package com.gientech.iot.rabbitWaiter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

/**
 * @description: rabbit服务员服务
 * @author: 王强
 * @dateTime: 2023-01-08 11:21:05
 */
@Slf4j
@Service
public class RabbitWaiterService {
    @Autowired
    private Barista barista;

    public void sendMessage(String message) {
        try {
            Message<String> msg = MessageBuilder.withPayload(message).build();
            barista.outputChannel().send(msg);
            log.info("rabbit服务员: 订单发送成功");
        } catch (Exception e) {
            throw new RuntimeException("Kafka服务员: 订单发送失败");
        }
    }

    @StreamListener(Barista.INPUT_CHANNEL)
    public void receiver(String message) {
        log.info("rabbit服务员接收到消息: " + message);
    }
}
