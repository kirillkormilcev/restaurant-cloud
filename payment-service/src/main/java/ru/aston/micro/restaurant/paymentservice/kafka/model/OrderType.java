package ru.aston.micro.restaurant.paymentservice.kafka.model;

import lombok.Getter;

@Getter
public enum OrderType {
  DRINK(100),
  PIZZA(300),
  SOUP(200);

  private final int price;

  OrderType(Integer price) {
     this.price = price;
  }
}

