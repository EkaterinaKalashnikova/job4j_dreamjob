package ru.job4j.dream.service;

import ru.job4j.dream.model.Post;

import java.util.List;

public class PostService {

    private PostService store;

    public List<Post> findAll() {
        return store.findAll();
    }

    public Post add() {
        return store.add();
    }

    public Post create() {
        return store.create();
    }

   public boolean update() {
        return store.update();
   }

   public Post findById() {
        return store.findById();
   }

    public boolean delete() {
        return store.delete();
    }
}
