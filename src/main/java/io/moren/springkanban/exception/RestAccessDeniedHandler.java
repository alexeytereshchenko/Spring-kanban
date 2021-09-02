package io.moren.springkanban.exception;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@AllArgsConstructor
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    private HandlerExceptionResolver handlerExceptionResolver;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        handlerExceptionResolver.resolveException(request, response, null, accessDeniedException);
    }
}
