package com.grizz.inventoryapp.inventory.service;

import com.grizz.inventoryapp.inventory.repository.entity.InventoryEntity;
import com.grizz.inventoryapp.inventory.service.domain.Inventory;
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
        return null;
//        return inventoryJpaRepository.findByItemId(itemId)
//                .map(inventoryEntity -> new Inventory(inventoryEntity.getItemId(), inventoryEntity.getStock()))
//                .orElse(null);
    }

    @Transactional
    public @NotNull Inventory decreaseByItemId(@NotNull String itemId, @NotNull Long quantity) {
        return null;
//        if (quantity < 0) {
//            throw new InvalidDecreaseQuantityException();
//        }
//
//        final InventoryEntity inventoryEntity = inventoryJpaRepository.findByItemId(itemId)
//                .orElseThrow(ItemNotFoundException::new);
//
//        if (inventoryEntity.getStock() < quantity) {
//            throw new InsufficientStockException();
//        }
//
//        final Integer updateCount = inventoryJpaRepository.decreaseStock(itemId, quantity);
//        if (updateCount == 0) {
//            throw new ItemNotFoundException();
//        }
//
//        final InventoryEntity updatedEntity = inventoryJpaRepository.findByItemId(itemId)
//                .orElseThrow(ItemNotFoundException::new);
//
//        return mapToDomain(updatedEntity);
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
