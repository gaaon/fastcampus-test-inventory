package com.grizz.inventoryapp.inventory.controller;

import com.grizz.inventoryapp.common.controller.ApiResponse;
import com.grizz.inventoryapp.inventory.controller.consts.ErrorCodes;
import com.grizz.inventoryapp.inventory.controller.dto.InventoryResponse;
import com.grizz.inventoryapp.inventory.controller.exception.CommonInventoryHttpException;
import com.grizz.inventoryapp.inventory.service.InventoryService;
import com.grizz.inventoryapp.inventory.service.domain.Inventory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/inventory")
@RestController
public class InventoryController {
    private final InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/{itemId}")
    ApiResponse<InventoryResponse> findByItemId(
            @PathVariable String itemId
    ) {
        final Inventory inventory = inventoryService.findByItemId(itemId);
        if (inventory == null) {
            throw new CommonInventoryHttpException(ErrorCodes.ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        return ApiResponse.just(
                InventoryResponse.fromDomain(inventory)
        );
    }
}