package com.grizz.inventoryapp.inventory.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Table(name = "inventory")
@Entity
public class InventoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private @Nullable Long id;
    private @NotNull String itemId;
    private @NotNull Long stock;

    public InventoryEntity(@Nullable Long id, @NotNull String itemId, @NotNull Long stock) {
        this.id = id;
        this.itemId = itemId;
        this.stock = stock;
    }

    public InventoryEntity() {
    }

    public Long getId() {
        return id;
    }

    public @NotNull String getItemId() {
        return itemId;
    }

    public @NotNull Long getStock() {
        return stock;
    }

    public void setStock(@NotNull Long stock) {
        this.stock = stock;
    }
}
