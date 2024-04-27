package com.grizz.inventoryapp.inventory.service.event;

import org.springframework.kafka.support.KafkaHeaders;

import org.jetbrains.annotations.NotNull;

public interface InventoryEventPublisher {
    String MESSAGE_KEY = KafkaHeaders.KEY;

    void publish(@NotNull InventoryEvent event);
}
