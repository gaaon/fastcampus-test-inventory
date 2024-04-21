package com.grizz.inventoryapp.inventory.repository;

import com.grizz.inventoryapp.inventory.repository.entity.InventoryEntity;
import com.grizz.inventoryapp.inventory.repository.jpa.InventoryJpaRepository;
import com.grizz.inventoryapp.inventory.service.domain.Inventory;
import com.grizz.inventoryapp.inventory.service.persistence.InventoryPersistenceAdapter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

@Component
public class InventoryPersistenceAdapterImpl implements InventoryPersistenceAdapter {
    private final InventoryJpaRepository inventoryJpaRepository;

    public InventoryPersistenceAdapterImpl(InventoryJpaRepository inventoryJpaRepository) {
        this.inventoryJpaRepository = inventoryJpaRepository;
    }

    @Override
    public @Nullable Inventory findByItemId(@NotNull String itemId) {
        return inventoryJpaRepository.findByItemId(itemId)
                .map(this::mapToDomain)
                .orElse(null);
    }

    @Override
    public @Nullable Inventory decreaseStock(@NotNull String itemId, @NotNull Long quantity) {
//        final Integer result = inventoryJpaRepository.decreaseStock(itemId, quantity);
//        if (result == 0) {
//            return null;
//        }

        return inventoryJpaRepository.findByItemId(itemId)
                .map(this::mapToDomain)
                .orElse(null);
    }

    @Override
    public @NotNull Inventory save(@NotNull Inventory inventory) {
        InventoryEntity entity = null;

        if (inventory.getId() != null) {
            entity = inventoryJpaRepository.findById(inventory.getId())
                    .orElse(null);
        }

        if (entity == null) {
            entity = mapToEntity(inventory);
        }

//        entity.setStock(inventory.getStock());

        return mapToDomain(inventoryJpaRepository.save(entity));
    }

    private @NotNull Inventory mapToDomain(@NotNull InventoryEntity entity) {
        return new Inventory(entity.getId(), entity.getItemId(), 0L);
    }

    private @NotNull InventoryEntity mapToEntity(@NotNull Inventory inventory) {
        return new InventoryEntity(inventory.getId(), inventory.getItemId(), 0L);
    }
}
