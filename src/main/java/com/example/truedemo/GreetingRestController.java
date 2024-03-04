package com.example.truedemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class GreetingRestController {

    @GetMapping("/greeting/{name}")
    public String greeting(@PathVariable String name) {
        log.info("Greeting {}", name);
        return "Hello, " + name + "!";
    }
}
