package com.grizz.inventoryapp.inventory.repository;

import com.grizz.inventoryapp.inventory.repository.kafka.InventoryDecreasedKafkaEvent;
import com.grizz.inventoryapp.inventory.repository.kafka.InventoryKafkaEvent;
import com.grizz.inventoryapp.inventory.service.event.InventoryDecreasedEvent;
import com.grizz.inventoryapp.inventory.service.event.InventoryEvent;
import com.grizz.inventoryapp.inventory.service.event.InventoryEventPublisher;
import org.jetbrains.annotations.NotNull;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class InventoryEventPublisherImpl implements InventoryEventPublisher {
    private final StreamBridge streamBridge;

    public InventoryEventPublisherImpl(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }

    @Override
    public void publish(@NotNull InventoryEvent event) {
        final InventoryKafkaEvent kafkaEvent = createFromDomainEvent(event);

        final Message<InventoryKafkaEvent> message = MessageBuilder.withPayload(kafkaEvent)
                .setHeader(InventoryEventPublisher.MESSAGE_KEY, kafkaEvent.itemId())
                .build();

        streamBridge.send("inventory-out-0", message);
    }

    private InventoryKafkaEvent createFromDomainEvent(@NotNull InventoryEvent event) {
        return switch (event) {
            case InventoryDecreasedEvent decreasedEvent ->
                    InventoryDecreasedKafkaEvent.fromDomainEvent(decreasedEvent);
            default -> throw new IllegalArgumentException("Unknown event type: " + event);
        };
    }
}
