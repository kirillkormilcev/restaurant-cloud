package ru.aston.micro.restaurant.kafkamodule.producer;

import ru.aston.micro.restaurant.kafkamodule.model.Outbox;

public interface KafkaProducer {

  boolean sendMessage(Outbox outbox);
}
