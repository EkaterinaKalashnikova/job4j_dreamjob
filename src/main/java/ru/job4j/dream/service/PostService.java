package ru.job4j.dream.service;

import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Post;
import ru.job4j.dream.store.PostStore;
import java.util.Collection;

@Service
public class PostService {

    private final PostStore store;

    private PostService(PostStore store) {
        this.store = store;
    }

    public Collection<Post> findAll() {
        return store.findAll();
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
}
