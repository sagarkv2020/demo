package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SwaggerController {
    @GetMapping("/swaggerUI")
    public String swaggerUI() {
        return "redirect:/swagger-ui.html";
    }
    @GetMapping("/docs")
    public String docs() {
        return "redirect:/v2/api-docs";
    }
}
