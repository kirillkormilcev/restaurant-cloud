package ru.aston.micro.restaurant.kafkamodule.producer;

import ru.aston.micro.restaurant.kafkamodule.model.Outbox;

public interface OutboxProducer {

  void sendMessageToKafka(Outbox outbox);
}
