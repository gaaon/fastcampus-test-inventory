package com.grizz.inventoryapp.inventory.repository;

import com.grizz.inventoryapp.inventory.repository.entity.InventoryEntity;
import com.grizz.inventoryapp.inventory.repository.jpa.InventoryJpaRepository;
import com.grizz.inventoryapp.inventory.service.domain.Inventory;
import com.grizz.inventoryapp.inventory.service.persistence.InventoryPersistenceAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InventoryPersistenceAdapterImpl implements InventoryPersistenceAdapter {
    private final InventoryJpaRepository inventoryJpaRepository;

    public InventoryPersistenceAdapterImpl(InventoryJpaRepository inventoryJpaRepository) {
        this.inventoryJpaRepository = inventoryJpaRepository;
    }

    @Override
    public @Nullable Inventory findByItemId(@NotNull String itemId) {
        return inventoryJpaRepository.findByItemId(itemId)
                .map(this::mapToDomain)
                .orElse(null);
    }

    @Override
    public @Nullable Inventory decreaseStock(@NotNull String itemId, @NotNull Long quantity) {
        return null;
    }

    @Override
    public @NotNull Inventory save(@NotNull Inventory inventory) {
        return null;
    }

    private @NotNull Inventory mapToDomain(@NotNull InventoryEntity entity) {
        return new Inventory(entity.getId(), entity.getItemId(), entity.getStock());
    }
}
