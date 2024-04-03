package com.grizz.inventoryapp.inventory.repository;

import com.grizz.inventoryapp.inventory.repository.entity.InventoryEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("h2-test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class InventoryJpaRepositoryTest {
    @Autowired
    InventoryJpaRepository sut;

    @Nested
    class FindByItemId {
        final String existingItemId = "1";
        final String nonExistingItemId = "2";
        final Long stock = 100L;

        @DisplayName("itemId를 갖는 entity가 없다면, empty를 반환한다")
        @Test
        void test1() {
            // when
            final Optional<InventoryEntity> result = sut.findByItemId(nonExistingItemId);

            // then
            assertTrue(result.isEmpty());
        }

        @DisplayName("itemId를 갖는 entity가 있다면, entity를 반환한다")
        @Test
        void test2() {
            // when
            final Optional<InventoryEntity> result = sut.findByItemId(existingItemId);

            // then
            assertTrue(result.isPresent());

            final InventoryEntity entity = result.get();
            assertEquals(existingItemId, entity.getItemId());
            assertEquals(stock, entity.getStock());
        }
    }

    @Nested
    class DecreaseStock {
        final String existingItemId = "1";
        final String nonExistingItemId = "2";
        final Long stock = 100L;

        @DisplayName("itemId를 갖는 entity가 없다면, 0을 반환한다")
        @Test
        void test1() {
            // when
            final Integer result = sut.decreaseStock(nonExistingItemId, 10L);

            // then
            assertEquals(0, result);
        }

        @DisplayName("itemId를 갖는 entity가 있다면, stock을 차감하고 1을 반환한다")
        @Test
        void test2() {
            // when
            final Integer result = sut.decreaseStock(existingItemId, 10L);

            // then
            assertEquals(1, result);

            final InventoryEntity entity = sut.findByItemId(existingItemId).get();
            final Long expectedStock = stock - 10L;
            assertEquals(expectedStock, entity.getStock());
        }
    }

    @Nested
    class Save {
        final Long existingId = 1L;
        final String existingItemId = "1";
        final String nonExistingItemId = "2";

        @DisplayName("id를 갖는 entity가 없다면, entity를 추가하고 추가된 entity를 반환한다")
        @Test
        void test1() {
            // given
            final Long newStock = 1234L;

            // when
            final InventoryEntity entity = new InventoryEntity(null, nonExistingItemId, newStock);
            final InventoryEntity result = sut.save(entity);

            // then
            assertNotNull(result.getId());
            assertEquals(nonExistingItemId, result.getItemId());
            assertEquals(newStock, result.getStock());
        }

        @DisplayName("id를 갖는 entity가 있다면, entity를 수정하고 수정된 entity를 반환한다")
        @Test
        void test2() {
            // given
            final Long newStock = 1234L;
            final InventoryEntity entity = sut.findByItemId(existingItemId).get();

            // when
            entity.setStock(newStock);
            final InventoryEntity result = sut.save(entity);

            // then
            assertEquals(1L, result.getId());
            assertEquals(existingItemId, result.getItemId());
            assertEquals(newStock, result.getStock());
        }
    }
}