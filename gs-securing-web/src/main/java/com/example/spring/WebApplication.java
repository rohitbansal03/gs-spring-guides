package com.example.spring;

import com.example.spring.db.LoadInitialDataRunner;
import com.example.spring.db.domain.Role;
import com.example.spring.db.domain.User;
import com.example.spring.db.repository.RoleRepository;
import com.example.spring.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(value = "spring.load.sample.data", havingValue = "true", matchIfMissing = false)
    public CommandLineRunner loadInitialData() {
        return  new LoadInitialDataRunner();
    }

}
