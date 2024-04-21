package com.grizz.inventoryapp.inventory.repository.redis;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class InventoryRedisRepositoryStub implements InventoryRedisRepository {
    private final Map<String, Long> stockMap = new HashMap<>();

    public void addStock(String itemId, Long stock) {
        stockMap.put(key(itemId), stock);
    }

    @Override
    public @Nullable Long getStock(@NotNull String itemId) {
        return stockMap.get(key(itemId));
    }
}
