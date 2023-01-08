package com.gientech.iot.rabbitWaiter;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Barista {
    String INPUT_CHANNEL = "finishOrder";
    String OUTPUT_CHANNEL = "newOrder";

    @Input(INPUT_CHANNEL)
    SubscribableChannel inputChannel();

    @Output(OUTPUT_CHANNEL)
    MessageChannel outputChannel();
}
