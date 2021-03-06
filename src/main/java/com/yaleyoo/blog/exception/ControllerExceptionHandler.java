package com.yaleyoo.blog.exception;

import com.yaleyoo.blog.response.SimpleHttpResult;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by steve on 14/3/19.
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    /**
     *
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(BlogNotFoundException.class)
    public ResponseEntity<SimpleHttpResult> handleBadRequest(Exception exception, WebRequest request){
        return new ResponseEntity<>(
                new SimpleHttpResult(false, exception.getMessage()),
                HttpStatus.BAD_REQUEST);
    }
}
