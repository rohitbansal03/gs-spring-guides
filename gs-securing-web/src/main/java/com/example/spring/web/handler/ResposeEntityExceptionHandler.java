package com.example.spring.web.handler;

import com.example.spring.util.WebUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ResposeEntityExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String INVALID_REQUEST = "Message cannot be parsed due to invalid request";

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {

        return WebUtil.getErrorResponse(Arrays.asList(INVALID_REQUEST), headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
                                                                  final HttpHeaders headers, final HttpStatus status,
                                                                  final WebRequest request) {

        List<String> errors = ex.getBindingResult().getAllErrors()
                .stream().map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());

        return WebUtil.getErrorResponse(errors, headers, status);
    }

}
