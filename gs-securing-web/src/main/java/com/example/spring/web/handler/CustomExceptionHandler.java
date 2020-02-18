package com.example.spring.web.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String INVALID_REQUEST = "Message cannot be parsed due to invalid request";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {

        return getCustomErrorResponse(Arrays.asList(INVALID_REQUEST), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        return getCustomErrorResponse(errors, headers, status);
    }

    private ResponseEntity<Object> getCustomErrorResponse(final List<String> errors,
                                                          final HttpHeaders headers, final HttpStatus status) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }

}
