package com.grizz.inventoryapp.inventory.repository.kafka;

import com.grizz.inventoryapp.inventory.service.event.InventoryUpdatedEvent;
import org.jetbrains.annotations.NotNull;

public record InventoryUpdatedKafkaEvent(
        @NotNull InventoryKafkaEventType type,
        @NotNull String itemId,
        @NotNull Long stock
) implements InventoryKafkaEvent {
    public static InventoryUpdatedKafkaEvent fromDomainEvent(InventoryUpdatedEvent event) {
        return new InventoryUpdatedKafkaEvent(
                InventoryKafkaEventType.InventoryUpdated,
                event.itemId(),
                event.stock()
        );
    }
}
