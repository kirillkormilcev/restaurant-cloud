package ru.aston.micro.restaurant.kafkamodule.service;

import java.util.List;

public interface OutboxService {

  void sendMessageToKafka();

  void createMessageForKafkaOutbox(Object object, String topic);

  void createMessageForKafkaOutbox(List<Object> object, String topic);
}
