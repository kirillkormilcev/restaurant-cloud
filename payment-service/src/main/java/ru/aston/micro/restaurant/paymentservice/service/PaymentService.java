package ru.aston.micro.restaurant.paymentservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.aston.micro.restaurant.paymentservice.repository.PaymentRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class PaymentService {
  final PaymentRepository paymentRepository;
}
