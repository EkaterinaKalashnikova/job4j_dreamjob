package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;
import java.util.List;

@Service
@ThreadSafe
public class PostService {

    private final PostStore store;
    private CityService cityService;
    private Post post;
    private List<Post> posts;

    private PostService(PostStore store) {
        this.store = store;
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

    public Post findById(int id) {
        return store.findById(id);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }

    public List<Post> findAll() {
        posts = (List<Post>) store.findAll();
        posts.forEach(
                post -> post.setCity(
                        cityService.findById(post.getCity().getId())
                )
        );
        return (List<Post>) store.findAll();
    }
}
