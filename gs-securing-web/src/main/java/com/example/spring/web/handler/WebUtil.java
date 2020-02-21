package com.example.spring.web.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class WebUtil {

    public static ResponseEntity<Object> getErrorResponse(final List<String> errors, final HttpStatus status) {
        HttpHeaders httpHeaders = new HttpHeaders();
        return getErrorResponse(errors, httpHeaders, status);
    }

    public static ResponseEntity<Object> getErrorResponse(final List<String> errors,
                                                          final HttpHeaders headers, final HttpStatus status) {

        Map<String, Object> body = getErrorMap(errors, status);
        return new ResponseEntity<>(body, headers, status);
    }

    public static Map<String, Object> getErrorMap(final List<String> errors, final HttpStatus status) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("errors", errors);
        return body;
    }

}
