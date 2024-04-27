package com.grizz.inventoryapp.test.binder;

import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class KafkaOutputDestination {
    public Message<byte[]> receive(long timeout, @NotNull String destination) {
        return null;
    }
}
