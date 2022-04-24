package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostDBStore;
import ru.job4j.dream.store.PostStore;

import java.util.List;

@Service
@ThreadSafe
public class PostService {
    private final PostStore store;
    private CityService cityService;

    private PostService(PostStore store, CityService cityService) {
        this.store = store;
        this.cityService = cityService;
    }

    public Post add(Post post) {
        return store.add(post);
    }

    public Post create(Post post) {
        return store.create(post);
    }

    public boolean update(Post post) {
        return store.update(post);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public List<Post> findAll() {
        List<Post> posts = store.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return store.findAll();
    }
}
