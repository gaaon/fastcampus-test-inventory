package com.grizz.inventoryapp.inventory.service.persistence;

import com.grizz.inventoryapp.inventory.service.domain.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InventoryPersistenceAdapterStub implements InventoryPersistenceAdapter {
    public void addInventory(@NotNull String itemId, @NotNull Long stock) {
    }

    @Override
    public @Nullable Inventory findByItemId(@NotNull String itemId) {
        return null;
    }

    @Override
    public @Nullable Inventory decreaseStock(@NotNull String itemId, @NotNull Long quantity) {
        return null;
    }

    @Override
    public @NotNull Inventory save(@NotNull Inventory inventory) {
        return null;
    }
}
