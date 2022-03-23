package com.ftc.exception;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter()
@Setter()
public class ErrorResponse {
  private String message;
  private List<String> details;

  public ErrorResponse(String message, List<String> details) {
    this.message = message;
    this.details = details;
  }
}
