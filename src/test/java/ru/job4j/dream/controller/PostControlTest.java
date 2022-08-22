package ru.job4j.dream.controller;

import org.junit.Test;
import org.springframework.ui.Model;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.service.CityService;
import ru.job4j.dream.service.PostService;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;

public class PostControlTest {
    @Test
    public void whenPosts() {
        List<Post> posts = Arrays.asList(
                new Post(1, "New post"),
                new Post(2, "New post")
        );
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findAll()).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostControl postControl = new PostControl(
                postService,
                cityService
        );

        String page = postControl.posts(model, session);
        verify(model).addAttribute("posts", posts);
        assertThat(page, is("posts"));
    }

    @Test
    public void whenAddPosts() {
        Post posts = new Post(0, "Заполните поле");
        Model model = mock(Model.class);
        HttpSession session = mock(HttpSession.class);
        PostService postService = mock(PostService.class);
        when(postService.findById(0)).thenReturn(posts);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(
                postService,
                cityService
        );
        String page = postController.addPost(model, session);
        verify(model).addAttribute("post", posts);
        verify(model).addAttribute("cities", cityService.getAllCities());
        assertThat(page, is("addPost"));
    }

      @Test
    public void whenFormUpdatePost() {
        Post input = new Post(1, "post", "old", LocalDateTime.now(), true, new City(1, "Anapa"));
        Model model = mock(Model.class);
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(
                postService,
                cityService
        );
        postController.createPost(input);
        String formUpdate = postController.formUpdatePost(model, input.getId());
        verify(model).addAttribute("post", postService.findById(input.getId()));
        verify(model).addAttribute("cities", cityService.getAllCities());
        assertThat(formUpdate, is("updatePost"));
    }

    @Test
    public void whenUpdatePost() {
        Post input = new Post(1, "post", "old", LocalDateTime.now(), true, new City(1, "Anapa"));
        Post newInput = new Post(1, "New post", "new", LocalDateTime.now(), true, new City(1, "Yeisk"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(
                postService,
                cityService
        );
        postController.createPost(input);
        String update = postController.updatePost(newInput);
        verify(postService).update(input);
        assertThat(update, is("redirect:/posts"));
    }

    @Test
    public void whenCreatePost() {
        Post input = new Post(1, "post", "old", LocalDateTime.now(), true, new City(1, "Anapa"));
        PostService postService = mock(PostService.class);
        CityService cityService = mock(CityService.class);
        PostControl postController = new PostControl(
                postService,
                cityService
        );
        String page = postController.createPost(input);
        verify(postService).add(input);
        assertThat(page, is("redirect:/posts"));
    }
}