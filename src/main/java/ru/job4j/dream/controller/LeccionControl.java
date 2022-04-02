package ru.job4j.dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeccionControl {
    @GetMapping("/leccion")
    public String leccion() {
        return "leccion";
    }
}
