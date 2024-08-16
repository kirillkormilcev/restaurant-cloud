package ru.aston.micro.restaurant.orderservice.kafkaproducer.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

  @Value("${topics.payment-request.name}")
  private String paymentRequestTopic;

  @Value("${topics.payment-response.name}")
  private String paymentResponseTopic;

  @Value("${topics.restaurant-request.name}")
  private String restaurantRequestTopic;

  @Value("${topics.restaurant-response.name}")
  private String restaurantResponseTopic;

  @Bean
  public NewTopic paymentRequestTopic() {
    return TopicBuilder.name(paymentRequestTopic).partitions(1).build();
  }

  @Bean
  public NewTopic paymentResponseTopic() {
    return TopicBuilder.name(paymentResponseTopic).partitions(1).build();
  }

  @Bean
  public NewTopic restaurantRequestTopic() {
    return TopicBuilder.name(restaurantRequestTopic).partitions(1).build();
  }

  @Bean
  public NewTopic restaurantResponseTopic() {
    return TopicBuilder.name(restaurantResponseTopic).partitions(1).build();
  }
}
