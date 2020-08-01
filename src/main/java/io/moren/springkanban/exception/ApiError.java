package io.moren.springkanban.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiError {

    private final HttpStatus status;
    private final Integer code;
    private final String time;
}
