package com.grizz.inventoryapp.inventory.controller.exception;

import com.grizz.inventoryapp.inventory.controller.consts.ErrorCodes;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;

public class CommonInventoryHttpException extends RuntimeException {
    @NotNull
    private final ErrorCodes errorCodes;

    @NotNull
    private final HttpStatus httpStatus;

    public CommonInventoryHttpException(
            @NotNull ErrorCodes errorCodes,
            @NotNull HttpStatus httpStatus
    ) {
        this.errorCodes = errorCodes;
        this.httpStatus = httpStatus;
    }

    @NotNull
    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    @NotNull
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}