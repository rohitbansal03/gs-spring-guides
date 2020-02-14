package com.example.spring.web.controller;

import com.example.spring.web.service.GreetingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GreetingController {

    private static Logger logger = LoggerFactory.getLogger(GreetingController.class);

    private final GreetingService service;

    public GreetingController(GreetingService service) {
        this.service = service;
    }

    @GetMapping("/greeting")
    public @ResponseBody String greeting() {
        logger.debug("Sending greeting message ...");
        return service.greet();
    }

}
