package com.mario.movilred.conciliation.exception;

public class ConciliationFileException extends Exception {

  private static final long serialVersionUID = 1L;

  public ConciliationFileException(String message) {
    super(message);
  }

  public ConciliationFileException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
