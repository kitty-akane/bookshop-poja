package school.hei.td.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends ApiException {

  public NotFoundException(String message, HttpStatus status) {
    super(message, HttpStatus.NOT_FOUND);
  }
}
