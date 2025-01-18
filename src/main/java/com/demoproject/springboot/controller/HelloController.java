package com.demoproject.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/helloWorld")
    public String displayMessage(){
        return "Congratulations your app is deployed sucessfully...!!!";
    }
}
