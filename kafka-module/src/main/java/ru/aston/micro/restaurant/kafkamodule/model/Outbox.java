package ru.aston.micro.restaurant.kafkamodule.model;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

@Getter
@Setter
@Entity
@Table(name = "outbox")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Outbox {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(name = "partition_key")
  private String partitionKey;

  @Column(name = "topic")
  private String topic;

  @Type(JsonType.class)
  @Column(name = "payload", columnDefinition = "jsonb")
  private Object payload;

  public Outbox(Object payload, String topic) {
    this.topic = topic;
    this.payload = payload;
  }
}
