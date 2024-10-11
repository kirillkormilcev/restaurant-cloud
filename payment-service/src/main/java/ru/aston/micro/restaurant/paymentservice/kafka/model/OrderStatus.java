package ru.aston.micro.restaurant.paymentservice.kafka.model;

public enum OrderStatus {
  PENDING,
  PAID,
  CANCELLING,
  CANCELLED,
  APPROVED
}
