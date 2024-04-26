package com.grizz.inventoryapp.inventory.repository.kafka;

public sealed interface InventoryKafkaEvent permits InventoryDecreasedKafkaEvent {
    String itemId();
}
