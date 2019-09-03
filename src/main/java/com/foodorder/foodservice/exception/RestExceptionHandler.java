package com.foodorder.foodservice.exception;

import com.foodorder.foodservice.exception.custom.CategoryNotFoundException;
import com.foodorder.foodservice.exception.custom.FoodNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request) {
        return buildResponseEntity(ApiError.builder()
                .message("Invalid request")
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        return buildResponseEntity(ApiError.builder()
                .message(ex.getParameterName() + " parameter is missing")
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST)
                .build());
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers,
                                                                         HttpStatus status,
                                                                         WebRequest request) {
        return buildResponseEntity(ApiError.builder()
                .message("Invalid request method")
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.METHOD_NOT_ALLOWED)
                .build());
    }

    @ExceptionHandler(FoodNotFoundException.class)
    private ResponseEntity<Object> handleFoodNotFoundException(FoodNotFoundException ex) {
        return buildResponseEntity(ApiError.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND).build());
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    private ResponseEntity<Object> handleCategoryNotFoundException(CategoryNotFoundException ex) {
        return buildResponseEntity(ApiError.builder()
                .message(ex.getMessage())
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.NOT_FOUND).build());
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
