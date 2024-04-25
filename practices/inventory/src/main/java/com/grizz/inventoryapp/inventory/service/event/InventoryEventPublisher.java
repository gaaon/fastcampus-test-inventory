package com.grizz.inventoryapp.inventory.service.event;

import org.jetbrains.annotations.NotNull;

public interface InventoryEventPublisher {
    String MESSAGE_KEY = "custom_messageKey";

    void publish(@NotNull InventoryEvent event);
}
