package com.example.spring;

import com.example.spring.db.LoadMasterDataRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(value = "spring.load.sample.data", havingValue = "true")
    public CommandLineRunner loadInitialData() {
        return  new LoadMasterDataRunner();
    }

}
