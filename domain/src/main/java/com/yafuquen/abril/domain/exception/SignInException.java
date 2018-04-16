package com.yafuquen.abril.domain.exception;

/**
 * Exception for sign in process.
 *
 * @author yafuquen
 */
public class SignInException extends Exception {

  public SignInException() {
    super();
  }

  public SignInException(Exception e) {
    super(e);
  }
}
