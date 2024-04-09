package com.grizz.inventoryapp.inventory.controller.dto;

import com.grizz.inventoryapp.inventory.service.domain.Inventory;
import org.jetbrains.annotations.NotNull;

public record InventoryResponse(
        @NotNull String itemId,
        @NotNull Long stock
) {
    public static InventoryResponse fromDomain(@NotNull Inventory inventory) {
        return new InventoryResponse(
                inventory.getItemId(),
                inventory.getStock()
        );
    }
}
