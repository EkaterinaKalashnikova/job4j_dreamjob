package ru.job4j.dream.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.City;
import ru.job4j.dream.model.Post;
import java.time.LocalDateTime;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostDBStoreTest {

    private PostDBStore store;

    @Before
    public void init() {
        store = new PostDBStore(new Main().loadPool());
    }

    @After
    public void end() {
        List<Post> all = store.findAll();
        for (Post pst : all) {
            this.store.delete(pst.getId());
        }
    }

    @Test
    public void whenCreatePost() {
        Post post = new Post(0, "name", "description", LocalDateTime.now(), true, new City(0, "Barcelona"));
        this.store.add(post);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(post.getName()));
    }

    @Test
    public void whenUpdatePost() {
        Post post = new Post(0, "name", "description", LocalDateTime.now(), true, new City(0, "Madrid"));
        this.store.add(post);
        Post update = new Post(post.getId(), "cat", "description", post.getCreated(), true, new City(1, "Santiago"));
        store.update(update);
        Post postInDb = store.findById(post.getId());
        assertThat(postInDb.getName(), is(update.getName()));
    }

    @Test
    public void whenDeletePost() {
        Post post = new Post(0, "name", "description", LocalDateTime.now(), true, new City(0, "Anapa"));
        this.store.add(post);
        this.store.delete(post.getId());
        assertThat(store.findById(post.getId()), is(nullValue()));
    }
}