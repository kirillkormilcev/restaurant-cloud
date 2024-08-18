package ru.aston.micro.restaurant.paymentservice.kafka.service;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.aston.micro.restaurant.paymentservice.kafka.configuration.KafkaTopic;
import ru.aston.micro.restaurant.paymentservice.kafka.model.Order;
import ru.aston.micro.restaurant.paymentservice.model.PaymentStatus;
import ru.aston.micro.restaurant.paymentservice.kafka.model.OrderStatus;
import ru.aston.micro.restaurant.paymentservice.model.Payment;
import ru.aston.micro.restaurant.paymentservice.repository.PaymentRepository;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE)
public class KafkaPaymentListener {
  final PaymentRepository paymentRepository;
  final ObjectMapper objectMapper;
  final KafkaPaymentProducer producer;
  final KafkaTopic kafkaTopic;

  @KafkaListener(topics = "payment_request_topic", groupId = "payment")
  public void listen(ConsumerRecord<String, String> recordOrder) throws JsonProcessingException {

    Order order = objectMapper.readValue(recordOrder.value(), Order.class);

    switch (order.getOrderStatus()) {
      case PENDING: // новый
        if (order.getUserAccount() > order.getOrderType().getPrice()) { //денег хватает
          order.setOrderStatus(OrderStatus.APPROVED);
          order.setUserAccount(order.getUserAccount() - order.getOrderType().getPrice());
          paymentRepository.save(Payment.builder()
              .orderId(order.getId())
              .userId(order.getUserId())
              .paymentStatus(PaymentStatus.COMPLETED)
              .build());

          sendMessageToKafka(order);
        } else { //денег не хватает
          order.setOrderStatus(OrderStatus.CANCELLED);
          paymentRepository.save(Payment.builder()
              .orderId(order.getId())
              .userId(order.getUserId())
              .paymentStatus(PaymentStatus.REJECTED)
              .build());

          sendMessageToKafka(order);
        }
        break;
      case PAID: //уже оплачен
        order.setOrderStatus(OrderStatus.APPROVED);
        paymentRepository.save(Payment.builder()
            .orderId(order.getId())
            .userId(order.getUserId())
            .paymentStatus(PaymentStatus.COMPLETED)
            .build());

        sendMessageToKafka(order);
        break;
      case CANCELLING: //отменен клиентом
        order.setOrderStatus(OrderStatus.CANCELLED);

        sendMessageToKafka(order);
        break;
      case CANCELLED: //уже отменен сервисом

        sendMessageToKafka(order);
        break;
      case APPROVED: //уже одобрен
        paymentRepository.save(Payment.builder()
            .orderId(order.getId())
            .userId(order.getUserId())
            .paymentStatus(PaymentStatus.COMPLETED)
            .build());

        sendMessageToKafka(order);
        break;
    }
  }

  private void sendMessageToKafka(Order order) throws JsonProcessingException {
    producer.sendMessage(
        kafkaTopic.getPaymentResponseTopic(),
        objectMapper.writeValueAsString(order));
  }
}
