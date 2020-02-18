package com.example.spring.web.controller;

import com.example.spring.web.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingRestController extends AbstractRestController {

    private static Logger logger = LoggerFactory.getLogger(GreetingRestController.class);

    private final GreetingService service;

    public GreetingRestController(GreetingService service) {
        this.service = service;
    }

    @GetMapping("/greeting")
    public String greeting() {
        logger.debug("Sending greeting message ...");
        return service.greet();
    }

}
