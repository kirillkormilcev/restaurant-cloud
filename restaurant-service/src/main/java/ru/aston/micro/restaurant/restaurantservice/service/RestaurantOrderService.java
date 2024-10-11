package ru.aston.micro.restaurant.restaurantservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import ru.aston.micro.restaurant.restaurantservice.repository.RestaurantOrderRepository;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RestaurantOrderService {
  final RestaurantOrderRepository restaurantOrderRepository;
}
