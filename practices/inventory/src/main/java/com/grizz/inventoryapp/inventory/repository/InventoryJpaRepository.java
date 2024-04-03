package com.grizz.inventoryapp.inventory.repository;

import com.grizz.inventoryapp.inventory.repository.entity.InventoryEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryJpaRepository extends JpaRepository<InventoryEntity, Long> {
    @NotNull Optional<InventoryEntity> findByItemId(@NotNull String itemId);

    @NotNull default Integer decreaseStock(@NotNull String itemId, @NotNull Long quantity) {
        return -1;
    }
}
