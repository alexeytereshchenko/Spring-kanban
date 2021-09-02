package io.moren.springkanban.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;
import java.util.NoSuchElementException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex,
                                                             Object body,
                                                             HttpHeaders headers,
                                                             HttpStatus status,
                                                             WebRequest request) {
        ApiErrorResponse error = createApiError(status);
        return super.handleExceptionInternal(ex, error, headers, status, request);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ApiErrorResponse> handleInternalServer(Exception e) {
        log.error("Internal server error: {}", e.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiErrorResponse error = createApiError(status);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({
            AccessDeniedException.class,
            AuthenticationException.class
    })
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<Object> refreshTokenHandler(RuntimeException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ApiErrorResponse error = createApiError(status, e.getMessage());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({
            ResourceNotFoundException.class,
            NoSuchElementException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiErrorResponse> handleNotFound() {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse error = createApiError(status);
        return new ResponseEntity<>(error, status);
    }

    private ApiErrorResponse createApiError(HttpStatus status) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setCode(status.value());
        error.setTime(Instant.now().getEpochSecond());
        error.setMessage(status.getReasonPhrase());
        return error;
    }

    private ApiErrorResponse createApiError(HttpStatus status, String message) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setCode(status.value());
        error.setTime(Instant.now().getEpochSecond());
        error.setMessage(message);
        return error;
    }
}

@Data
class ApiErrorResponse {
    private Long time;
    private Integer code;
    private String message;
}
