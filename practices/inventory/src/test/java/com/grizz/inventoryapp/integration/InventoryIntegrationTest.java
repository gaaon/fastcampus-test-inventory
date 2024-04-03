package com.grizz.inventoryapp.integration;

import com.grizz.inventoryapp.inventory.controller.consts.ErrorCodes;
import com.grizz.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("integration-test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(scripts = {"classpath:schema.sql", "classpath:data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InventoryIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    final String existingItemId = "1";
    final String nonExistingItemId = "2";
    final Long stock = 100L;

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
    void test2() throws Exception {
        mockMvc.perform(get("/api/v1/inventory/{itemId}", existingItemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.item_id").value(existingItemId))
                .andExpect(jsonPath("$.data.stock").value(stock));
    }

    @DisplayName("재고 차감 실패")
    @Test
    void test3() throws Exception {
        // 1. 재고를 조회하고 100개인 것을 확인한다.
        successGetStock(existingItemId, stock);

        // 2. 재고 110개를 차감하고 실패한다.
        final Long quantity = 110L;
        final String requestBody = "{\"quantity\": " + quantity + "}";
        mockMvc.perform(
                        post("/api/v1/inventory/{itemId}/decrease", existingItemId)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.error.code").value(ErrorCodes.INSUFFICIENT_STOCK.code))
                .andExpect(jsonPath("$.error.local_message").value(ErrorCodes.INSUFFICIENT_STOCK.message));

        // 3. 재고를 조회하고 100개인 것을 확인한다.
        successGetStock(existingItemId, stock);
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

    private void successGetStock(String itemId, Long stock) throws Exception {
        mockMvc.perform(get("/api/v1/inventory/{itemId}", itemId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.item_id").value(itemId))
                .andExpect(jsonPath("$.data.stock").value(stock));
    }
}
