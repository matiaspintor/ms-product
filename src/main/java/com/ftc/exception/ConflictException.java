package com.ftc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {
  private static final long serialVersionUID = 8779999169311384750L;

  public ConflictException(String exception) {
    super(exception);
  }
}
