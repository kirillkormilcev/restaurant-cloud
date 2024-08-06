package ru.aston.micro.restaurant.kafkamodule.producer.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.aston.micro.restaurant.kafkamodule.model.Outbox;
import ru.aston.micro.restaurant.kafkamodule.producer.OutboxProducer;
import ru.aston.micro.restaurant.kafkamodule.producer.KafkaProducer;
import ru.aston.micro.restaurant.kafkamodule.repository.OutboxRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class OutboxProducerImpl implements OutboxProducer {

  private final KafkaProducer kafkaProducer;
  private final OutboxRepository outboxRepository;

  @Override
  @Async
  @Transactional
  public void sendMessageToKafka(Outbox outbox) {
    if (kafkaProducer.sendMessage(outbox)) {
      outboxRepository.delete(outbox);
      log.info("Message with id: {} successfully sent to Kafka", outbox.getId());

    } else {
      log.info("Failed to send message to Kafka. Message with id: {}.", outbox.getId());
    }
  }
}
