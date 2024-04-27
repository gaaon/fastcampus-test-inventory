package com.grizz.inventoryapp.inventory.repository.kafka;

import com.grizz.inventoryapp.inventory.service.event.InventoryDecreasedEvent;
import org.jetbrains.annotations.NotNull;

public record InventoryDecreasedKafkaEvent(
        @NotNull InventoryKafkaEventType type,
        @NotNull String itemId,
        @NotNull Long quantity,
        @NotNull Long stock
) implements InventoryKafkaEvent {
    public static InventoryDecreasedKafkaEvent fromDomainEvent(InventoryDecreasedEvent event) {
        return new InventoryDecreasedKafkaEvent(
                InventoryKafkaEventType.InventoryDecreased,
                event.itemId(),
                event.quantity(),
                event.stock()
        );
    }
}
