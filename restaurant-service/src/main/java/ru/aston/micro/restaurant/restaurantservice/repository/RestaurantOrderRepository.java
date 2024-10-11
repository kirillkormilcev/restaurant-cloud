package ru.aston.micro.restaurant.restaurantservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.aston.micro.restaurant.restaurantservice.model.RestaurantOrder;

@Repository
public interface RestaurantOrderRepository extends JpaRepository<RestaurantOrder, Long> {
}
