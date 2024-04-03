package com.grizz.inventoryapp.arch;

import com.grizz.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ArchUnitTest {
    @DisplayName("Controller 레이어는 Service 레이어에만 의존한다")
    @Test
    void test1() {
        throw new NotImplementedTestException();
    }

    @DisplayName("Service 레이어는 그 어떤 레이어에 의존하지 않는다")
    @Test
    void test2() {
        throw new NotImplementedTestException();
    }

    @DisplayName("Repository 레이어는 Service 레이어에만 의존한다")
    @Test
    void test3() {
        throw new NotImplementedTestException();
    }
}