package ru.aston.micro.restaurant.kafkamodule.exception;

import static ru.aston.micro.restaurant.kafkamodule.exception.ExceptionType.INTERNAL_SERVER_ERROR;

public class InternalServerError extends BaseException {

  public InternalServerError() {
    super(INTERNAL_SERVER_ERROR);
  }

  public InternalServerError(String message) {
    super(INTERNAL_SERVER_ERROR, message);
  }

}
