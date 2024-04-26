package com.grizz.inventoryapp.inventory.service.event;

import org.jetbrains.annotations.NotNull;

public record InventoryDecreasedEvent(
        @NotNull String itemId,
        @NotNull Long quantity,
        @NotNull Long stock
) implements InventoryEvent {
}
