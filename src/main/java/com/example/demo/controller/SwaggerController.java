package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {
    @GetMapping({"/docs", "/swagger"})
    public String docs() {
        return "redirect:/swagger-ui.html";
    }
}
