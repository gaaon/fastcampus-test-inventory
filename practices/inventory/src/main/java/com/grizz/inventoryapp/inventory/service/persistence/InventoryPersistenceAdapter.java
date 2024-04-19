package com.grizz.inventoryapp.inventory.service.persistence;

import com.grizz.inventoryapp.inventory.service.domain.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface InventoryPersistenceAdapter {
    @Nullable Inventory findByItemId(@NotNull String itemId);

    @Nullable Inventory decreaseStock(@NotNull String itemId, @NotNull Long quantity);

    @NotNull Inventory save(@NotNull Inventory inventory);
}
