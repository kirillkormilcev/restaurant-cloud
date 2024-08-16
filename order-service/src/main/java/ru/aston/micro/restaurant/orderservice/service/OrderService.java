package ru.aston.micro.restaurant.orderservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.aston.micro.restaurant.orderservice.model.Order;
import ru.aston.micro.restaurant.orderservice.repository.OrderRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class OrderService {
  final OrderRepository orderRepository;

  final KafkaTemplate<String, String> kafkaTemplate;

  public Order create(Order order) {
    kafkaTemplate.send("payment_request_topic", "test");
    return orderRepository.save(order);
  }

}
