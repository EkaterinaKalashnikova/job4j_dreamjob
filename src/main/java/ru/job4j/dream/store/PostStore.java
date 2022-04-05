package ru.job4j.dream.store;

import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PostStore {

    private static final PostStore INST = new PostStore();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "умеет программировать, "
                + "мало опыта на рельных задачах, знает 10-20% используемых в проекте технологий", LocalDateTime.now()));
        posts.put(2, new Post(2, "Middle Java Job", "в состоянии решать сложные задачи самостоятельно, знает 50-60% используемых в проекте технологий", LocalDateTime.now()));
        posts.put(3, new Post(3, "Senior Java Job", " берет на себя ответственность за проект, самостоятельно. "
                + "Знает более 90% используемых технологий. В состоянии решать «нерешаемые» проблемы. "
                + "Помогает другим: ставит им задачи, учит. В критических ситуациях берет на себя ответственность и риски", LocalDateTime.now()));
    }

    public static PostStore instOf() {
        return INST;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }

    public Post add(Post post) {
       return posts.putIfAbsent(0, post);
    }
}