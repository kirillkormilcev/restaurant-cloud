package ru.aston.micro.restaurant.restaurantservice.model;

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
@Table(name = "restaurant_order")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EqualsAndHashCode
@ToString
public class RestaurantOrder {
  @Id
  @SequenceGenerator(name = "restaurant_order_seq", allocationSize = 1)
  @GeneratedValue(generator = "restaurant_order_seq", strategy = GenerationType.SEQUENCE)
  Long id;

  @Column(nullable = false)
  Long orderId;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  RestaurantOrderStatus restaurantOrderStatus;
}
