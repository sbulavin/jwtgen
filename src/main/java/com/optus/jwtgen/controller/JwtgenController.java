package com.optus.jwtgen.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by optus on 28.10.17.
 */

@RestController
public class JwtgenController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }
}
