package com.example.spring.config;

import com.example.spring.web.handler.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class CustomAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    private static final HttpStatus UNAUTHORIZED = HttpStatus.UNAUTHORIZED;
    private static final String USERNAME_PASSWORD_IS_INCORRECT = "Username/password is incorrect";

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(final HttpServletRequest request, final HttpServletResponse response,
                         final AuthenticationException authException) throws IOException {

        response.addHeader("WWW-Authenticate", "Basic realm=\"" + this.getRealmName() + "\"");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(UNAUTHORIZED.value());
        response.getWriter().write(objectMapper.writeValueAsString(
                WebUtil.getErrorMap(Arrays.asList(USERNAME_PASSWORD_IS_INCORRECT), UNAUTHORIZED)));
    }
}
