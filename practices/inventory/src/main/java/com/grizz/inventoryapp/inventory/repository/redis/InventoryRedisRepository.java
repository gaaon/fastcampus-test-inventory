package com.grizz.inventoryapp.inventory.repository.redis;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface InventoryRedisRepository {
    @NotNull default String key(@NotNull String itemId) {
        return "inventory:" + itemId;
    }

    @Nullable Long getStock(@NotNull String itemId);
}
