package ru.aston.micro.restaurant.kafkamodule.service.impl;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.aston.micro.restaurant.kafkamodule.model.Outbox;
import ru.aston.micro.restaurant.kafkamodule.producer.OutboxProducer;
import ru.aston.micro.restaurant.kafkamodule.repository.OutboxRepository;
import ru.aston.micro.restaurant.kafkamodule.service.OutboxService;

@Service
@Slf4j
@RequiredArgsConstructor
public class OutboxServiceImpl implements OutboxService {

  private final OutboxProducer outboxProducer;
  private final OutboxRepository outboxRepository;


  @Override
  @Scheduled(fixedRate = 1000)
  public void sendMessageToKafka() {
    List<Outbox> messages = outboxRepository.findAll();
    messages.parallelStream().forEach(outboxProducer::sendMessageToKafka);
  }

  @Override
  public void createMessageForKafkaOutbox(Object message, String topic) {
    log.info("Creating message for Kafka Outbox.");
    Outbox outbox = Outbox.builder()
        .topic(topic)
        .payload(message)
        .build();

    outboxRepository.save(outbox);
  }

  @Override
  public void createMessageForKafkaOutbox(List<Object> messages, String topic) {
    log.info("Creating list messages for Kafka Outbox.");
    List<Outbox> outboxMessages = messages.stream()
        .map(message -> new Outbox(message, topic))
        .toList();

    outboxRepository.saveAll(outboxMessages);
  }
}
