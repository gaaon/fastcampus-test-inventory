package com.grizz.inventoryapp.inventory.service;

import com.grizz.inventoryapp.inventory.service.domain.Inventory;
import com.grizz.inventoryapp.inventory.service.event.InventoryDecreasedEvent;
import com.grizz.inventoryapp.inventory.service.event.InventoryEvent;
import com.grizz.inventoryapp.inventory.service.event.InventoryEventPublisher;
import com.grizz.inventoryapp.inventory.service.event.InventoryUpdatedEvent;
import com.grizz.inventoryapp.inventory.service.exception.InsufficientStockException;
import com.grizz.inventoryapp.inventory.service.exception.InvalidDecreaseQuantityException;
import com.grizz.inventoryapp.inventory.service.exception.InvalidStockException;
import com.grizz.inventoryapp.inventory.service.exception.ItemNotFoundException;
import com.grizz.inventoryapp.inventory.service.persistence.InventoryPersistenceAdapterStub;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InventoryServiceTest {
    @InjectMocks
    InventoryService sut;

    @Spy
    InventoryPersistenceAdapterStub inventoryAdapter;

    @Mock
    InventoryEventPublisher inventoryEventPublisher;

    @Nested
    class FindByItemId {
        final String existingItemId = "1";
        final Long stock = 10L;

        @BeforeEach
        void setUp() {
            inventoryAdapter.addInventory(existingItemId, stock);
        }

        @DisplayName("itemId를 갖는 entity를 찾지 못하면, null을 반환한다")
        @Test
        void test1() {
            // given
            final String nonExistingItemId = "2";

            // when
            final Inventory result = sut.findByItemId(nonExistingItemId);

            // then
            assertNull(result);
        }

        @DisplayName("itemId를 갖는 entity를 찾으면, inventory를 반환한다")
        @Test
        void test1000() {
            // when
            final Inventory result = sut.findByItemId(existingItemId);

            // then
            assertNotNull(result);
            assertEquals(existingItemId, result.getItemId());
            assertEquals(stock, result.getStock());
        }
    }

    @Nested
    class DecreaseByItemId {
        final String existingItemId = "1";
        final Long stock = 10L;

        @BeforeEach
        void setUp() {
            inventoryAdapter.addInventory(existingItemId, stock);
        }

        @DisplayName("quantity가 음수라면, Exception을 throw한다")
        @Test
        void test1() {
            // given
            final Long quantity = -1L;

            // when, then
            assertThrows(InvalidDecreaseQuantityException.class, () -> {
                sut.decreaseByItemId(existingItemId, quantity);
            });
        }

        @DisplayName("itemId를 갖는 entity를 찾지 못하면, Exception을 throw한다")
        @Test
        void test2() {
            // given
            final String nonExistingItemId = "2";
            final Long quantity = 10L;

            // when, then
            assertThrows(ItemNotFoundException.class, () -> {
                sut.decreaseByItemId(nonExistingItemId, quantity);
            });
        }

        @DisplayName("quantity가 stock보다 크면, Exception을 throw한다")
        @Test
        void test3() {
            // given
            final Long quantity = stock + 1;

            // when, then
            assertThrows(InsufficientStockException.class, () -> {
                sut.decreaseByItemId(existingItemId, quantity);
            });
        }


        @DisplayName("변경된 entity가 없다면, Exception을 throw한다")
        @Test
        void test4() {
            // given
            final Long quantity = 10L;

            // 불가피하게 0을 반환하도록 stubbing
            doReturn(null).when(inventoryAdapter).decreaseStock(existingItemId, quantity);

            // when, then
            assertThrows(ItemNotFoundException.class, () -> {
                sut.decreaseByItemId(existingItemId, quantity);
            });

            // inventoryJpaRepository.decreaseStock가 실제로 동작했는지 궁금하기 때문에 verify로 검증
            verify(inventoryAdapter).decreaseStock(existingItemId, quantity);
        }

        @DisplayName("itemId를 갖는 entity를 찾으면, stock을 차감하고 inventory를 반환한다")
        @Test
        void test1000() {
            // given
            final Long quantity = 10L;

            // when
            final Inventory result = sut.decreaseByItemId(existingItemId, quantity);

            // then
            assertNotNull(result);
            assertEquals(existingItemId, result.getItemId());
            assertEquals(stock - quantity, result.getStock());

            final InventoryEvent expectedEvent = new InventoryDecreasedEvent(existingItemId, quantity, stock - quantity);
            verify(inventoryEventPublisher).publish(expectedEvent);
        }
    }

    @Nested
    class UpdateStock {
        final String existingItemId = "1";
        final Long stock = 10L;

        @BeforeEach
        void setUp() {
            inventoryAdapter.addInventory(existingItemId, stock);
        }

        @DisplayName("수정할 stock이 유효하지 않다면, Exception을 throw한다")
        @Test
        void test1() {
            // given
            final Long stock = -1L;

            // when, then
            assertThrows(InvalidStockException.class, () -> {
                sut.updateStock(existingItemId, stock);
            });
        }

        @DisplayName("itemId를 갖는 entity를 찾지 못하면, Exception을 throw한다")
        @Test
        void test2() {
            // given
            final String nonExistingItemId = "2";
            final Long stock = 10L;

            // when, then
            assertThrows(ItemNotFoundException.class, () -> {
                sut.updateStock(nonExistingItemId, stock);
            });
        }

        @DisplayName("itemId를 갖는 entity를 찾으면, stock을 수정하고 inventory를 반환한다")
        @Test
        void test1000() {
            // given
            final Long newStock = 20L;

            // when
            final Inventory result = sut.updateStock(existingItemId, newStock);

            // then
            assertNotNull(result);
            assertEquals(existingItemId, result.getItemId());
            assertEquals(newStock, result.getStock());

            final InventoryEvent expectedEvent = new InventoryUpdatedEvent(existingItemId, newStock);
            verify(inventoryEventPublisher).publish(expectedEvent);
        }
    }
}