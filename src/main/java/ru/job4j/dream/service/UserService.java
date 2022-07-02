package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.User;
import ru.job4j.dream.store.UserDBStore;
import java.util.List;
import java.util.Optional;

@Service
@ThreadSafe
public class UserService {
    private final UserDBStore userDBStore;

    public UserService(UserDBStore userDBStore) {
        this.userDBStore = userDBStore;
    }

    public Optional<User> add(User user) {
        return this.userDBStore.add(user);
    }

    public List<User> findAll() {
        return this.userDBStore.findAll();
    }

    public  Optional<User> findUserByEmail(String email, String password) {
        return this.userDBStore.findUserByEmail(email, password);
    }
}
