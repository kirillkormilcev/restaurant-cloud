package ru.aston.micro.restaurant.kafkamodule.producer.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import ru.aston.micro.restaurant.kafkamodule.exception.InternalServerError;
import ru.aston.micro.restaurant.kafkamodule.model.Outbox;
import ru.aston.micro.restaurant.kafkamodule.producer.KafkaProducer;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducerImpl implements KafkaProducer {

  private final KafkaTemplate<String, Object> kafkaTemplate;

  @Override
  public boolean sendMessage(Outbox outbox) {
    return !kafkaTemplate.send(
            outbox.getTopic(),
            outbox.getPartitionKey(),
            outbox.getPayload()
        )
        .whenComplete(this::logSendingResult)
        .isCompletedExceptionally();
  }

  private void logSendingResult(SendResult<String, Object> result, Throwable ex) {
    if (ex == null) {
      log.info("Sent someDto, " +
              "in topic {}, partition {}, offset {}, Timestamp {}",
          result.getRecordMetadata().topic(),
          result.getRecordMetadata().partition(),
          result.getRecordMetadata().offset(),
          result.getRecordMetadata().hasTimestamp());
    } else {
      throw new InternalServerError("Отправка события завершилась не удачно.");
    }
  }
}
