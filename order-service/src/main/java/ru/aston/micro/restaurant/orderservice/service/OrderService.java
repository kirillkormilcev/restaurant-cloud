package ru.aston.micro.restaurant.orderservice.service;

import static lombok.AccessLevel.PRIVATE;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.aston.micro.restaurant.orderservice.kafka.configuration.KafkaTopic;
import ru.aston.micro.restaurant.orderservice.kafka.service.KafkaOrderProducer;
import ru.aston.micro.restaurant.orderservice.model.Order;
import ru.aston.micro.restaurant.orderservice.repository.OrderRepository;

@Service
@FieldDefaults(level = PRIVATE)
@RequiredArgsConstructor
public class OrderService {
  final OrderRepository orderRepository;
  final KafkaOrderProducer producer;
  final KafkaTopic kafkaTopic;
  final ObjectMapper objectMapper;

  public Order create(Order order) throws JsonProcessingException {

    order = orderRepository.save(order);
    producer.sendMessage(
        kafkaTopic.getPaymentRequestTopic(),
        objectMapper.writeValueAsString(order));
    return order;
  }

  @KafkaListener(topics = "payment_response_topic", groupId = "payment")
  public void listen(ConsumerRecord<String, String> recordOrder) throws JsonProcessingException {

    Order order = objectMapper.readValue(recordOrder.value(), Order.class);

    orderRepository.save(order);
  }

}
