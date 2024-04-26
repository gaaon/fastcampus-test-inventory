package com.grizz.inventoryapp.inventory.repository;

import com.grizz.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InventoryEventPublisherTest {
    @Nested
    class InventoryDecreasedEventTest {
        @DisplayName("InventoryDecreasedEvent 객체를 publish하면, 메시지가 발행된다")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }
    }

    @Nested
    class InventoryUpdatedEventTest {
        @DisplayName("InventoryUpdatedEvent 객체를 publish하면, 메시지가 발행된다")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }
    }
}
