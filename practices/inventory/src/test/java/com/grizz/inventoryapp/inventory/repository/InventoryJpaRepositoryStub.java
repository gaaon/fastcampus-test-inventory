package com.grizz.inventoryapp.inventory.repository;

import com.grizz.inventoryapp.inventory.repository.entity.InventoryEntity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InventoryJpaRepositoryStub implements InventoryJpaRepository {
    private final List<InventoryEntity> inventoryEntities = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public void addInventoryEntity(String itemId, Long stock) {
        final Long id = idGenerator.getAndIncrement();
        final InventoryEntity inventoryEntity = new InventoryEntity(id, itemId, stock);
        inventoryEntities.add(inventoryEntity);
    }

    @Override
    public @NotNull Optional<InventoryEntity> findByItemId(@NotNull String itemId) {
        return internalFindByItemId(itemId);
    }

    @Override
    public @NotNull Integer decreaseStock(@NotNull String itemId, @NotNull Long quantity) {
        final Optional<InventoryEntity> optionalEntity = internalFindByItemId(itemId);

        if (optionalEntity.isEmpty()) {
            return 0;
        }

        final InventoryEntity entity = optionalEntity.get();
        final Long newStock = entity.getStock() - quantity;
        entity.setStock(newStock);

        return 1;
    }

    @Override
    public @NotNull InventoryEntity save(@NotNull InventoryEntity inventoryEntity) {
        final Long entityId = inventoryEntity.getId();
        final Optional<InventoryEntity> optionalEntity = inventoryEntities.stream()
                .filter(entity -> entity.getId() != null && entity.getId().equals(entityId))
                .findFirst();

        InventoryEntity entity;

        if (optionalEntity.isPresent()) {
            entity = optionalEntity.get();
            entity.setStock(inventoryEntity.getStock());
        } else {
            final Long id = idGenerator.getAndIncrement();
            entity = new InventoryEntity(id, inventoryEntity.getItemId(), inventoryEntity.getStock());
            inventoryEntities.add(entity);
        }

        return entity;
    }

    private Optional<InventoryEntity> internalFindByItemId(String itemId) {
        return inventoryEntities.stream()
                .filter(entity -> entity.getItemId().equals(itemId))
                .findFirst();
    }
}
