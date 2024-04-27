package com.grizz.inventoryapp.test.assertion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.grizz.inventoryapp.inventory.controller.consts.ErrorCodes;
import com.grizz.inventoryapp.inventory.service.event.InventoryEventPublisher;
import org.jetbrains.annotations.NotNull;
import org.springframework.messaging.Message;
import org.springframework.test.web.servlet.MvcResult;

import java.io.UnsupportedEncodingException;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Assertions {
    private static final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);

    public static void assertMvcErrorEquals(
            MvcResult result, ErrorCodes errorCodes
    ) throws UnsupportedEncodingException, JsonProcessingException {
        final String content = result.getResponse().getContentAsString();
        final var responseBody = objectMapper.readTree(content);
        final var errorField = responseBody.get("error");

        assertNotNull(errorField);
        assertTrue(errorField.isObject());
        assertEquals(errorCodes.code, errorField.get("code").asLong());
        assertEquals(errorCodes.message, errorField.get("local_message").asText());
    }

    public static void assertMvcDataEquals(
            MvcResult result, Consumer<JsonNode> consumer
    ) throws UnsupportedEncodingException, JsonProcessingException {
        final String content = result.getResponse().getContentAsString();
        final var responseBody = objectMapper.readTree(content);
        final var dataField = responseBody.get("data");

        assertNotNull(dataField);
        assertTrue(dataField.isObject());

        consumer.accept(dataField);
    }

    public static void assertDecreasedEventEquals(
            @NotNull Message<byte[]> result, @NotNull String itemId, @NotNull Long quantity, @NotNull Long stock
    ) throws JsonProcessingException {
        final String payload = new String(result.getPayload());
        final JsonNode json = objectMapper.readTree(payload);
        assertEquals("InventoryDecreased", json.get("type").asText());
        assertEquals(itemId, json.get("item_id").asText());
        assertEquals(quantity, json.get("quantity").asLong());
        assertEquals(stock, json.get("stock").asLong());

        final String messageKey = result.getHeaders().get(InventoryEventPublisher.MESSAGE_KEY, String.class);
        assertEquals(itemId, messageKey);
    }

    public static void assertUpdatedEventEquals(
            @NotNull Message<byte[]> result, @NotNull String itemId, @NotNull Long stock
    ) throws JsonProcessingException {
        final String payload = new String(result.getPayload());
        final JsonNode json = objectMapper.readTree(payload);
        assertEquals("InventoryUpdated", json.get("type").asText());
        assertEquals(itemId, json.get("item_id").asText());
        assertEquals(stock, json.get("stock").asLong());

        final String messageKey = result.getHeaders().get(InventoryEventPublisher.MESSAGE_KEY, String.class);
        assertEquals(itemId, messageKey);
    }
}
