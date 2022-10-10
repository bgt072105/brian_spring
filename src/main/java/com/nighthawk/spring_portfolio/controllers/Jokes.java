package com.nighthawk.spring_portfolio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // HTTP requests are handled as a controller, using the @Controller annotation
public class Jokes {

    // CONTROLLER handles GET request for /birds, maps it to birds() method
    @GetMapping("/jokes")
    public String jokes() {

        // load HTML VIEW (jokes.html)
        return "jokes";

    }
}

