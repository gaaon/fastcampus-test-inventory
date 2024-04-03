package com.grizz.inventoryapp.integration;

import com.grizz.inventoryapp.inventory.controller.consts.ErrorCodes;
import com.grizz.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InventoryIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    final String nonExistingItemId = "2";

    @DisplayName("재고 조회 실패")
    @Test
    void test1() throws Exception {
        // given
        mockMvc.perform(get("/api/v1/inventory/{itemId}", nonExistingItemId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error.code").value(ErrorCodes.ITEM_NOT_FOUND.code))
                .andExpect(jsonPath("$.error.local_message").value(ErrorCodes.ITEM_NOT_FOUND.message));
    }

    @DisplayName("재고 조회 성공")
    @Test
    void test2() {
        throw new NotImplementedTestException();
    }

    @DisplayName("재고 차감 실패")
    @Test
    void test3() {
        throw new NotImplementedTestException();
    }

    @DisplayName("재고 차감 성공")
    @Test
    void test4() {
        throw new NotImplementedTestException();
    }

    @DisplayName("재고 수정 실패")
    @Test
    void test5() {
        throw new NotImplementedTestException();
    }

    @DisplayName("재고 수정 성공")
    @Test
    void test6() {
        throw new NotImplementedTestException();
    }

    @DisplayName("재고 차감, 수정 종합")
    @Test
    void test7() {
        throw new NotImplementedTestException();
    }
}
