package com.grizz.inventoryapp.inventory.repository.redis;

import com.grizz.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InventoryRedisRepositoryTest {
    InventoryRedisRepository sut;

    final String existingItemId = "1";
    final String nonExistingItemId = "2";
    final Long stock = 100L;

    @Nested
    class GetStock {
        @DisplayName("itemId를 갖는 inventory가 없다면, null을 반환한다")
        @Test
        void test1() {
            // when
            final Long result = sut.getStock(nonExistingItemId);

            // then
            assertNull(result);
        }

        @DisplayName("itemId를 갖는 inventory가 있다면, stock을 반환한다")
        @Test
        void test2() {
            // when
            final Long result = sut.getStock(existingItemId);

            // then
            assertEquals(stock, result);
        }
    }

    @Nested
    class DecreaseStock {
        @DisplayName("itemId를 갖는 inventory가 없다면, inventory를 생성하고 stock을 차감하고 반환한다")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }

        @DisplayName("itemId를 갖는 inventory가 있다면, stock을 차감하고 반환한다")
        @Test
        void test2() {
            throw new NotImplementedTestException();
        }
    }

    @Nested
    class DeleteStock {
        @DisplayName("itemId를 갖는 inventory가 없다면, false를 반환한다")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }

        @DisplayName("itemId를 갖는 inventory가 있다면, stock을 삭제하고 true를 반환한다")
        @Test
        void test2() {
            throw new NotImplementedTestException();
        }
    }

    @Nested
    class SetStock {
        @DisplayName("itemId를 갖는 inventory가 없다면, inventory를 생성하고 stock을 수정하고 반환한다")
        @Test
        void test1() {
            throw new NotImplementedTestException();
        }

        @DisplayName("itemId를 갖는 inventory가 있다면, stock을 수정하고 반환한다")
        @Test
        void test2() {
            throw new NotImplementedTestException();
        }
    }
}
