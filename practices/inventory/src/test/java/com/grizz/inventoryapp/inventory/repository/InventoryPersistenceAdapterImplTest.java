package com.grizz.inventoryapp.inventory.repository;

import com.grizz.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class InventoryPersistenceAdapterImplTest {
    @Nested
    class FindByItemId {
        @DisplayName("itemId를 갖는 entity가 없다면, null을 반환한다")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }

        @DisplayName("itemId를 갖는 entity가 있다면, inventory를 반환한다")
        @Test
        void test2() {
            throw new NotImplementedTestException();
        }
    }

    @Nested
    class DecreaseByItemId {
        @DisplayName("itemId를 갖는 entity가 없다면, null을 반환한다")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }

        @DisplayName("itemId를 갖는 entity가 있다면, stock을 차감하고 inventory를 반환한다")
        @Test
        void test2() {
            throw new NotImplementedTestException();
        }
    }

    @Nested
    class Save {
        @DisplayName("id를 갖는 entity가 없다면, entity를 추가하고 추가된 inventory를 반환한다")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }

        @DisplayName("id를 갖는 entity가 있다면, entity를 수정하고 수정된 inventory를 반환한다")
        @Test
        void test2() {
            throw new NotImplementedTestException();
        }
    }
}
