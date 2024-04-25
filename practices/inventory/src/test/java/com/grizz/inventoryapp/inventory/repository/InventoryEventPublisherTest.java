package com.grizz.inventoryapp.inventory.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.grizz.inventoryapp.inventory.service.event.InventoryDecreasedEvent;
import com.grizz.inventoryapp.inventory.service.event.InventoryEvent;
import com.grizz.inventoryapp.inventory.service.event.InventoryEventPublisher;
import com.grizz.inventoryapp.test.exception.NotImplementedTestException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.messaging.Message;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryEventPublisherTest {
    InventoryEventPublisher sut;

    OutputDestination outputDestination;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    @Nested
    class InventoryDecreasedEventTest {
        final String itemId = "1";
        final Long quantity = 10L;
        final Long stock = 90L;

        @DisplayName("InventoryDecreasedEvent 객체를 publish하면, 메시지가 발행된다")
        @Test
        void test1() throws JsonProcessingException {
            // when
            final InventoryEvent event = new InventoryDecreasedEvent(itemId, quantity, stock);
            sut.publish(event);

            // then
            final Message<byte[]> result = outputDestination.receive(1000, "inventory-out-0");

            final String payload = new String(result.getPayload());
            final JsonNode json = objectMapper.readTree(payload);
            assertEquals("InventoryDecreased", json.get("type").asText());
            assertEquals(itemId, json.get("item_id").asText());
            assertEquals(quantity, json.get("quantity").asLong());
            assertEquals(stock, json.get("stock").asLong());

            final String messageKey = result.getHeaders().get(InventoryEventPublisher.MESSAGE_KEY, String.class);
            assertEquals(itemId, messageKey);
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
