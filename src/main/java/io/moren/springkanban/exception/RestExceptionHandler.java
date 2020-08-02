package io.moren.springkanban.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        ApiError apiError = new ApiError(
                httpStatus,
                httpStatus.value(),
                getCurrentLocalDateTimeStamp()
        );

        return handleExceptionInternal(ex, apiError, headers, httpStatus, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> notFoundHandler(Exception ex) {

        HttpStatus httpStatus = HttpStatus.NOT_FOUND;

        ApiError apiError = new ApiError(
                httpStatus,
                httpStatus.value(),
                getCurrentLocalDateTimeStamp()
        );

        return new ResponseEntity<>(apiError, httpStatus);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Object> authHandler(Exception ex) {

        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

        ApiError apiError = new ApiError(
                httpStatus,
                httpStatus.value(),
                getCurrentLocalDateTimeStamp()
        );

        return new ResponseEntity<>(apiError, httpStatus);
    }

    private String getCurrentLocalDateTimeStamp() {
        return LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
