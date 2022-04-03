package ru.job4j.dream.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.CandidateStore;

@Controller
public class CandidateControl {
    private final CandidateStore store = CandidateStore.instOf();

    @GetMapping("/candidates")
    public String posts(Model model) {
        model.addAttribute("candidates", store.findAll());
        return "candidates";
    }

    @GetMapping("/addCandidate")
    public String addPost(Model model) {
        model.addAttribute("candidate", new Candidate(0, "Заполните поле"));
        return "addCandidate";
    }
}
