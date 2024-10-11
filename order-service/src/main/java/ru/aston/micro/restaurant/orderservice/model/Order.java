package ru.aston.micro.restaurant.orderservice.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "orders")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
public class Order {
  @Id
  @SequenceGenerator(name = "orders_seq", allocationSize = 1)
  @GeneratedValue(generator = "orders_seq", strategy = GenerationType.SEQUENCE)
  Long id;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_type", nullable = false)
  OrderType orderType;

  @Column(name = "user_id", nullable = false)
  Long userId;

  @Column(name = "user_account", nullable = false)
  Long userAccount;

  @Enumerated(EnumType.STRING)
  @Column(name = "order_status", nullable = false)
  OrderStatus orderStatus;
}
