package ru.job4j.dream.store;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
@ThreadSafe
public class PostStore {

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private static final AtomicInteger ID = new AtomicInteger(4);

    private PostStore() {
        posts.put(1, new Post(1, "Junior Java Job", "умеет программировать, "
                + "мало опыта на рельных задачах, знает 10-20% используемых в проекте технологий", LocalDateTime.now(), new City(1, "Москва")));
        posts.put(2, new Post(2, "Middle Java Job", "в состоянии решать сложные задачи самостоятельно, знает 50-60% используемых в проекте технологий", LocalDateTime.now(), new City(2, "Екб")));
        posts.put(3, new Post(3, "Senior Java Job", " берет на себя ответственность за проект, самостоятельно. "
                + "Знает более 90% используемых технологий. В состоянии решать «нерешаемые» проблемы. "
                + "Помогает другим: ставит им задачи, учит. В критических ситуациях берет на себя ответственность и риски", LocalDateTime.now(), new City(3, "СПб")));
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public Post add(Post post) {
        post.setId(ID.getAndIncrement());
        return posts.putIfAbsent(post.getId(), post);
    }

    public boolean update(Post post) {
        boolean flag = false;
        Post pst = findById(post.getId());
        if (pst != null) {
            posts.replace(post.getId(), post);
            flag = true;
        }
        return flag;
    }

    public Post create(Post post) {
        Post pst = findById(post.getId());
        if (pst == null) {
            posts.computeIfAbsent(ID.getAndIncrement(), v -> {
                post.setId(v);
                return post;
            });
            return post;
        }
        return null;
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public boolean delete(int id) {
        if (findById(id) != null) {
            posts.remove(id);
            return true;
        }
        return false;
    }
}

