package com.grizz.inventoryapp.inventory.service.domain;

import org.jetbrains.annotations.NotNull;

public class Inventory {
    private @NotNull String itemId;
    private @NotNull Long stock;

    public Inventory(@NotNull String itemId, @NotNull Long stock) {
        this.itemId = itemId;
        this.stock = stock;
    }

    public @NotNull String getItemId() {
        return itemId;
    }

    public @NotNull Long getStock() {
        return stock;
    }
}
