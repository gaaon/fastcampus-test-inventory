package com.grizz.inventoryapp.test.binder;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;

@Component
public class KafkaOutputDestination {
    @Value("${spring.cloud.stream.kafka.binder.brokers}")
    private String brokers;

    public @NotNull Message<byte[]> receive(long timeout, @NotNull String destination) {
        final ConsumerRecord<String, String> record = getOneRecord(brokers, "inventory-group-1",
                "inventory", 0, false, true, Duration.ofSeconds(1));

        return convertToMessage(record);
    }

    private Message<byte[]> convertToMessage(@NotNull ConsumerRecord<String, String> record) {
        final byte[] payload = record.value().getBytes(StandardCharsets.UTF_8);

        return MessageBuilder.withPayload(payload)
                .setHeader(KafkaHeaders.OFFSET, record.offset())
                .setHeader(KafkaHeaders.PARTITION, record.partition())
                .setHeader(KafkaHeaders.TOPIC, record.topic())
                .setHeader(KafkaHeaders.TIMESTAMP, record.timestamp())
                .setHeader(KafkaHeaders.KEY, record.key())
                .build();
    }

    /**
     * @see KafkaTestUtils#getOneRecord
     */
    private ConsumerRecord<String, String> getOneRecord(String brokerAddresses, String group, String topic, int partition,
                                                        boolean seekToLast, boolean commit, Duration timeout) {

        Map<String, Object> consumerConfig = KafkaTestUtils.consumerProps(brokers, group, "false");
        consumerConfig.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        consumerConfig.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        try (KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerConfig)) {
            TopicPartition topicPart = new TopicPartition(topic, partition);
            consumer.assign(Collections.singletonList(topicPart));
            if (seekToLast) {
                consumer.seekToEnd(Collections.singletonList(topicPart));
                if (consumer.position(topicPart) > 0) {
                    consumer.seek(topicPart, consumer.position(topicPart) - 1);
                }
            }
            ConsumerRecords<String, String> records = consumer.poll(timeout);
            ConsumerRecord<String, String> record = records.count() == 1 ? records.iterator().next() : null;
            if (record != null && commit) {
                consumer.commitSync();
            }

            return record;
        }
    }
}
