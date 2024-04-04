package com.grizz.inventoryapp.inventory.service.domain;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class Inventory {
    private @Nullable Long id;
    private @NotNull String itemId;
    private @NotNull Long stock;

    public Inventory(@Nullable Long id, @NotNull String itemId, @NotNull Long stock) {
        this.id = id;
        this.itemId = itemId;
        this.stock = stock;
    }

    public void setId(@Nullable Long id) {
        this.id = id;
    }

    public @Nullable Long getId() {
        return id;
    }

    public @NotNull String getItemId() {
        return itemId;
    }

    public void setStock(@NotNull Long stock) {
        this.stock = stock;
    }

    public @NotNull Long getStock() {
        return stock;
    }
}
