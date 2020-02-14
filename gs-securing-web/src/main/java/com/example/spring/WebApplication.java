package com.example.spring;

import com.example.spring.db.domain.User;
import com.example.spring.db.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(UserRepository repository, PasswordEncoder passwordEncoder) {
        return (args) -> {
            User john = new User();
            john.setFirstName("John");
            john.setLastName("Doe");
            john.setEmail("john@google.com");
            john.setPassword(passwordEncoder.encode("password"));
            repository.save(john);
        };
    }

}
