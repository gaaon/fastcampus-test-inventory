package com.grizz.inventoryapp.inventory.repository.redis;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class InventoryRedisRepositoryImpl implements InventoryRedisRepository {
    private final StringRedisTemplate redisTemplate;

    public InventoryRedisRepositoryImpl(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public @Nullable Long getStock(@NotNull String itemId) {
        final String stockStr = redisTemplate.opsForValue().get(key(itemId));
        if (stockStr == null) {
            return null;
        }

        return Long.parseLong(stockStr);
    }

    @Override
    public @NotNull Long decreaseStock(@NotNull String itemId, @NotNull Long quantity) {
        return null;
    }

    @Override
    public Boolean deleteStock(@NotNull String itemId) {
        return null;
    }

    @Override
    public @NotNull Long setStock(@NotNull String itemId, @NotNull Long stock) {
        return null;
    }
}
