package com.grizz.inventoryapp.test.assertion;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.grizz.inventoryapp.inventory.controller.consts.ErrorCodes;
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
}
