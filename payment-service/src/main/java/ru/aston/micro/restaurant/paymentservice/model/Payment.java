package ru.aston.micro.restaurant.paymentservice.model;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;
import static lombok.AccessLevel.PUBLIC;

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
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Entity
@Table(name = "payment")
@FieldDefaults(level = PRIVATE)
@AllArgsConstructor(access = PROTECTED)
@NoArgsConstructor(access = PUBLIC)
@EqualsAndHashCode
@ToString
@Builder
public class Payment {
  @Id
  @SequenceGenerator(name = "payment_seq", allocationSize = 1)
  @GeneratedValue(generator = "payment_seq", strategy = GenerationType.SEQUENCE)
  Long id;

  @Column(name = "order_id", nullable = false)
  Long orderId;

  @Column(name = "user_id", nullable = false)
  Long userId;

  @Enumerated(EnumType.STRING)
  @Column(name = "payment_status", nullable = false)
  PaymentStatus paymentStatus;
}
