package ru.job4j.dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompanyControl {
    @GetMapping("/company")
    public String company() {
        return "company";
    }
}
