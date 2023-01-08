package com.gientech.iot.rabbitBarista;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Barista {
    String INPUT_CHANNEL = "newOrder";
    String OUTPUT_CHANNEL = "finishOrder";

    @Input(INPUT_CHANNEL)
    SubscribableChannel inputChannel();

    @Output(OUTPUT_CHANNEL)
    MessageChannel outputChannel();
}
