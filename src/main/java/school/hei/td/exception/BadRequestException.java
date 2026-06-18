package school.hei.td.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

  public BadRequestException(String message, HttpStatus status) {
    super(message, HttpStatus.BAD_REQUEST);
  }
}
