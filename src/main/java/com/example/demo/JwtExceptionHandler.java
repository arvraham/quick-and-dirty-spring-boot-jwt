
package com.example.demo;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class JwtExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity handleExpiredJwt (
            ExpiredJwtException exception, WebRequest request) {
        
        return handleExceptionInternal(exception, 
                exception.getMessage(), 
                new HttpHeaders(),
                HttpStatus.UNAUTHORIZED, 
                request);
        
    }
    
}
