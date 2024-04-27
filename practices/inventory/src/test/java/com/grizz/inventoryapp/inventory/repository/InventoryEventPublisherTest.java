package com.grizz.inventoryapp.inventory.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.grizz.inventoryapp.inventory.service.event.InventoryDecreasedEvent;
import com.grizz.inventoryapp.inventory.service.event.InventoryEvent;
import com.grizz.inventoryapp.inventory.service.event.InventoryEventPublisher;
import com.grizz.inventoryapp.inventory.service.event.InventoryUpdatedEvent;
import com.grizz.inventoryapp.test.binder.KafkaOutputDestination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.Message;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static com.grizz.inventoryapp.test.assertion.Assertions.assertDecreasedEventEquals;
import static com.grizz.inventoryapp.test.assertion.Assertions.assertUpdatedEventEquals;

@ActiveProfiles("kafka-binder-test")
@Testcontainers
@SpringBootTest
public class InventoryEventPublisherTest {
    @Container
    private static final KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.6.1"))
            .withKraft()
            .withEnv("KAFKA_AUTO_CREATE_TOPICS_ENABLE", "true")
            .withEnv("KAFKA_CREATE_TOPICS", "inventory");

    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.cloud.stream.kafka.binder.brokers", kafkaContainer::getBootstrapServers);
    }

    @Autowired
    InventoryEventPublisher sut;

    @Autowired
    KafkaOutputDestination outputDestination;

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
            assertDecreasedEventEquals(result, itemId, quantity, stock);
        }
    }

    @Nested
    class InventoryUpdatedEventTest {
        final String itemId = "1";
        final Long stock = 100L;

        @DisplayName("InventoryUpdatedEvent 객체를 publish하면, 메시지가 발행된다")
        @Test
        void test1() throws JsonProcessingException {
            // when
            final InventoryEvent event = new InventoryUpdatedEvent(itemId, stock);
            sut.publish(event);

            // then
            final Message<byte[]> result = outputDestination.receive(1000, "inventory-out-0");
            assertUpdatedEventEquals(result, itemId, stock);
        }
    }
}
