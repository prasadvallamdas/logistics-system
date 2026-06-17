package com.alpha.logisticsproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String home() {
        return "login";
    }

//    @GetMapping("/dashboard")
//    public String dashboard() {
//        return "index";
//    }

    @GetMapping("/drivers")
    public String drivers() {
        return "drivers";
    }

    @GetMapping("/trucks")
    public String trucks() {
        return "trucks";
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders";
    }
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
}