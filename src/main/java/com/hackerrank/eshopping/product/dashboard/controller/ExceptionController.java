package com.hackerrank.eshopping.product.dashboard.controller;

import com.hackerrank.eshopping.product.dashboard.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

    public ExceptionController() {
        super();
    }

    // API

    // 400

    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleBadRequest(final ConstraintViolationException ex, final WebRequest request) {
        final String bodyOfResponse = "Application Error";
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "Application Error";
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String bodyOfResponse = "Application Error";
        // ex.getCause() instanceof JsonMappingException, JsonParseException // for additional information later on
        return handleExceptionInternal(ex, bodyOfResponse, headers, HttpStatus.BAD_REQUEST, request);
    }


    // 412

    // 500

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class, IllegalStateException.class, Exception.class})
    /*500*/ public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
        final String bodyOfResponse = ex.toString();
        return handleExceptionInternal(ex,
                bodyOfResponse,
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }
}
