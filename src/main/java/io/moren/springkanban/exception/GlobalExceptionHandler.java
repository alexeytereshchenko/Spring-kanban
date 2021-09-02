package io.moren.springkanban.exception;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    public ResponseEntity<ApiErrorResponse> handleInternalServer(Exception e) {
        log.error("Internal server error: {}", e.getMessage());
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiErrorResponse error = createApiError(status);
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({
//            TokenRefreshException.class,
            AccessDeniedException.class,
            AuthenticationException.class
    })
    public ResponseEntity<Object> refreshTokenHandler(RuntimeException e) {
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        ApiErrorResponse error = new ApiErrorResponse();
        error.setTime(generateTime());
        error.setCode(status.value());
        error.setMessage(e.getMessage());
        return new ResponseEntity<>(error, status);
    }

    @ExceptionHandler({
            ResourceNotFoundException.class,
            NoSuchElementException.class
    })
    public ResponseEntity<ApiErrorResponse> handleNotFound() {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ApiErrorResponse error = createApiError(status);
        return new ResponseEntity<>(error, status);
    }

    private ApiErrorResponse createApiError(HttpStatus status) {
        ApiErrorResponse error = new ApiErrorResponse();
        error.setCode(status.value());
        error.setTime(generateTime());
        error.setMessage(status.getReasonPhrase());
        return error;
    }

    private String generateTime() {
        return LocalDateTime.now()
                .format(
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                );
    }
}

@Data
class ApiErrorResponse {
    private String time;
    private Integer code;
    private String message;
}
