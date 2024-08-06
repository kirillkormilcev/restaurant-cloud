package ru.aston.micro.restaurant.kafkamodule.repository;

import jakarta.validation.constraints.NotNull;
import ru.aston.micro.restaurant.kafkamodule.model.Outbox;
import java.util.List;
import java.util.UUID;
//import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OutboxRepository extends JpaRepository<Outbox, UUID> {

  @Override
  @NotNull //заменено с jetbrains
  List<Outbox> findAll();

  @Override
  void delete(@NotNull Outbox outbox); //@NotNull заменено с jetbrains
}
