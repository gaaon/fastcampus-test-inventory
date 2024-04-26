package com.grizz.inventoryapp.inventory.service.event;

import org.jetbrains.annotations.NotNull;

public interface InventoryEventPublisher {
    void publish(@NotNull InventoryEvent event);
}
