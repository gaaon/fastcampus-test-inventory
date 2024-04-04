package com.grizz.inventoryapp.inventory.service.persistence;

import com.grizz.inventoryapp.inventory.service.domain.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class InventoryPersistenceAdapterStub implements InventoryPersistenceAdapter {
    private final List<Inventory> inventoryList = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public void addInventory(String itemId, Long stock) {
        final Long id = idGenerator.getAndIncrement();
        final Inventory inventory = new Inventory(id, itemId, stock);
        inventoryList.add(inventory);
    }

    @Override
    public @Nullable Inventory findByItemId(@NotNull String itemId) {
        return internalFindByItemId(itemId).orElse(null);
    }

    @Override
    public @Nullable Inventory decreaseStock(@NotNull String itemId, @NotNull Long quantity) {
        final Optional<Inventory> optionalInventory = internalFindByItemId(itemId);

        if (optionalInventory.isEmpty()) {
            return null;
        }

        final Inventory inventory = optionalInventory.get();
        final Long newStock = inventory.getStock() - quantity;
        inventory.setStock(newStock);

        return inventory;
    }

    @Override
    public @NotNull Inventory save(@NotNull Inventory inventory) {
        final Long inventoryId = inventory.getId();
        final Optional<Inventory> optionalInventory = inventoryList.stream()
                .filter(entity -> entity.getId() != null && entity.getId().equals(inventoryId))
                .findFirst();

        Inventory entityToSave;

        if (optionalInventory.isPresent()) {
            entityToSave = optionalInventory.get();
            entityToSave.setStock(inventory.getStock());
        } else {
            final Long id = idGenerator.getAndIncrement();
            entityToSave = new Inventory(id, inventory.getItemId(), inventory.getStock());
            inventoryList.add(entityToSave);
        }

        return entityToSave;
    }

    private Optional<Inventory> internalFindByItemId(String itemId) {
        return inventoryList.stream()
                .filter(entity -> entity.getItemId().equals(itemId))
                .findFirst();
    }
}
