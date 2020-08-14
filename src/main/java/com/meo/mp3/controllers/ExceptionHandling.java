package com.meo.mp3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExceptionHandling {
    @GetMapping("/403")
    public String get403page() {
        return "403";
    }
}
