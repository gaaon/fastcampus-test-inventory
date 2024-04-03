package com.grizz.inventoryapp.inventory.service;

import com.grizz.inventoryapp.inventory.service.domain.Inventory;
import com.grizz.inventoryapp.inventory.service.exception.InsufficientStockException;
import com.grizz.inventoryapp.inventory.service.exception.InvalidDecreaseQuantityException;
import com.grizz.inventoryapp.inventory.service.exception.ItemNotFoundException;
import com.grizz.inventoryapp.inventory.service.persistence.InventoryPersistenceAdapter;
import jakarta.transaction.Transactional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
    private final InventoryPersistenceAdapter inventoryAdapter;

    public InventoryService(InventoryPersistenceAdapter inventoryAdapter) {
        this.inventoryAdapter = inventoryAdapter;
    }

    public @Nullable Inventory findByItemId(@NotNull String itemId) {
        return inventoryAdapter.findByItemId(itemId);
    }

    @Transactional
    public @NotNull Inventory decreaseByItemId(@NotNull String itemId, @NotNull Long quantity) {
        if (quantity < 0) {
            throw new InvalidDecreaseQuantityException();
        }

        final Inventory inventory = inventoryAdapter.findByItemId(itemId);
        if (inventory == null) {
            throw new ItemNotFoundException();
        }

        if (inventory.getStock() < quantity) {
            throw new InsufficientStockException();
        }

        final Inventory updatedInventory = inventoryAdapter.decreaseStock(itemId, quantity);
        if (updatedInventory == null) {
            throw new ItemNotFoundException();
        }

        return updatedInventory;
    }

    public @NotNull Inventory updateStock(@NotNull String itemId, @NotNull Long stock) {
        return null;
//        if (stock < 0) {
//            throw new InvalidStockException();
//        }
//
//        final InventoryEntity entity = inventoryJpaRepository.findByItemId(itemId)
//                .orElseThrow(ItemNotFoundException::new);
//
//        entity.setStock(stock);
//
//        return mapToDomain(inventoryJpaRepository.save(entity));
    }

//    private Inventory mapToDomain(InventoryEntity inventoryEntity) {
//        return new Inventory(inventoryEntity.getItemId(), inventoryEntity.getStock());
//    }
}
