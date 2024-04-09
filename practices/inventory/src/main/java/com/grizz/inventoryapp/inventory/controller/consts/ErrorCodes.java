package com.grizz.inventoryapp.inventory.controller.consts;

import org.jetbrains.annotations.NotNull;

public enum ErrorCodes {
    ITEM_NOT_FOUND("자산이 존재하지 않습니다", 1000L),
    INSUFFICIENT_STOCK("재고가 부족합니다", 1001L),
    INVALID_DECREASE_QUANTITY("차감 수량이 유효하지 않습니다", 1002L),
    INVALID_STOCK("재고가 유효하지 않습니다", 1003L);

    public final @NotNull String message;
    public final @NotNull Long code;

    ErrorCodes(@NotNull String message, @NotNull Long code) {
        this.message = message;
        this.code = code;
    }
}
