package com.grizz.inventoryapp.inventory.controller.dto;

import org.jetbrains.annotations.NotNull;

public record DecreaseQuantityRequest(
        @NotNull Long quantity
) {
}
