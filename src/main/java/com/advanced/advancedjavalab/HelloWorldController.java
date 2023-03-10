package com.advanced.advancedjavalab;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class HelloWorldController {
    @GetMapping("/hello")
    public String hello(
            @RequestParam(value = "name", defaultValue = "World")
            String name
    ){
        return String.format("Hello, %s!", name);
    }
}
