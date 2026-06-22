package school.hei.td.exception;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import school.hei.td.exception.model.ExceptionBody;

@RestControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ApiException.class)
  public ResponseEntity<ExceptionBody> handleApiException(
      ApiException apiException, HttpServletRequest request) {
    return ResponseEntity.status(apiException.getStatus().value())
        .body(
            new ExceptionBody(
                apiException.getStatus().value(),
                apiException.getStatus().getReasonPhrase(),
                apiException.getMessage(),
                request.getRequestURI(),
                Instant.now()));
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ExceptionBody> handleValidation(
      MethodArgumentNotValidException exception, HttpServletRequest request) {

    return ResponseEntity.badRequest()
        .body(
            new ExceptionBody(
                400, "Bad Request", "Validation failed", request.getRequestURI(), Instant.now()));
  }

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  public ResponseEntity<ExceptionBody> handleTypeMismatch(
      MethodArgumentTypeMismatchException exception, HttpServletRequest request) {
    return ResponseEntity.badRequest()
        .body(
            new ExceptionBody(
                400,
                "Bad Request",
                "Invalid parameter type",
                request.getRequestURI(),
                Instant.now()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionBody> handleException(
      Exception exception, HttpServletRequest request) {
    return ResponseEntity.internalServerError()
        .body(
            new ExceptionBody(
                500,
                "An internal error has occurred",
                exception.getMessage(),
                request.getRequestURI(),
                Instant.now()));
  }
}
