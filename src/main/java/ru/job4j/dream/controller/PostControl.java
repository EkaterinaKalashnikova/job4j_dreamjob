package ru.job4j.dream.controller;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.service.CityService;
import ru.job4j.dream.service.PostService;

import java.time.LocalDateTime;

@Controller
@ThreadSafe
public class PostControl {

    private final PostService postService;
    private final CityService cityService;

    public PostControl(PostService postService, CityService cityService) {
        this.postService = postService;
        this.cityService = cityService;
    }

    @GetMapping("/posts")
    public String posts(Model model) {
        model.addAttribute("posts", postService.findAll());
        return "posts";
    }

    @GetMapping("/addPost")
    public String addPost(Model model) {
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("post", new Post(0, "Заполните поле",
                "Заполните поле", null, false, null));
        return "addPost";
    }

    @GetMapping("/formUpdatePost/{postId}")
    public String formUpdatePost(Model model, @PathVariable("postId") int id) {
        model.addAttribute("cities", cityService.getAllCities());
        model.addAttribute("post", postService.findById(id));
        return "updatePost";
    }

    @PostMapping("/updatePost")
    public String updatePost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        postService.update(post);
        return "redirect:/posts";
    }

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute Post post) {
        post.setCity(cityService.findById(post.getCity().getId()));
        post.setCreated(LocalDateTime.now());
        postService.add(post);
        return "redirect:/posts";
    }
}