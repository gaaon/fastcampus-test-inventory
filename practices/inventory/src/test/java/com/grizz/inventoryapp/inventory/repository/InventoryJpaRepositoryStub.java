package com.grizz.inventoryapp.inventory.repository;

import com.grizz.inventoryapp.inventory.repository.entity.InventoryEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.FluentQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

@NoRepositoryBean
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

    @Override
    public void flush() {

    }

    @Override
    public <S extends InventoryEntity> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends InventoryEntity> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<InventoryEntity> entities) {
        InventoryJpaRepository.super.deleteInBatch(entities);
    }

    @Override
    public void deleteAllInBatch(Iterable<InventoryEntity> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> longs) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public InventoryEntity getOne(Long aLong) {
        return null;
    }

    @Override
    public InventoryEntity getById(Long aLong) {
        return null;
    }

    @Override
    public InventoryEntity getReferenceById(Long aLong) {
        return null;
    }

    @Override
    public <S extends InventoryEntity> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends InventoryEntity> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends InventoryEntity> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends InventoryEntity> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends InventoryEntity> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends InventoryEntity> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends InventoryEntity, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends InventoryEntity> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<InventoryEntity> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<InventoryEntity> findAll() {
        return null;
    }

    @Override
    public List<InventoryEntity> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(InventoryEntity entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends InventoryEntity> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<InventoryEntity> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<InventoryEntity> findAll(Pageable pageable) {
        return null;
    }
}
