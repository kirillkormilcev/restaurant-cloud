package ru.aston.micro.restaurant.kafkamodule.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

  private final ExceptionType type;
  private final HttpStatus httpStatus;

  public BaseException(ExceptionType type) {
    super(type.getMessage());
    this.type = type;
    this.httpStatus = type.getHttpStatus();
  }

  public BaseException(ExceptionType type, String message) {
    super(message);
    this.type = type;
    this.httpStatus = type.getHttpStatus();
  }
}

