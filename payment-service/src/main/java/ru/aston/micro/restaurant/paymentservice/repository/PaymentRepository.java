package ru.aston.micro.restaurant.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.micro.restaurant.paymentservice.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
