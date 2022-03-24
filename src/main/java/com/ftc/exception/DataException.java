package com.ftc.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DataException extends Exception {

  private static final long serialVersionUID = 5436136707693773092L;
  private String msjError = "";

  public DataException(String message) {
    super(message);
  }

  public DataException(Exception e) {
    super(e);
    msjError = e.getMessage();
  }

  public DataException(Exception e, String msg) {
    super(e);

    msjError = msg;
  }
}
