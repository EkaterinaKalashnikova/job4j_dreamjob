package ru.job4j.dream.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.User;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDBStore {
    private static final Logger LOG = LoggerFactory.getLogger(UserDBStore.class.getName());
    private final BasicDataSource pool;

    public UserDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection cn = pool.getConnection(); PreparedStatement ps = cn.prepareStatement("SELECT * FROM users")) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    int id = it.getInt("id");
                    String name = it.getString("name");
                    String email = it.getString("email");
                    String password = it.getString("password");
                    LocalDateTime created = it.getTimestamp("created").toLocalDateTime();
                    users.add(new User(id, name, email, password, created));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }

    public  Optional<User> add(User user) {
        Optional<User> total = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO users(name, email, password, created) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setTimestamp(4, Timestamp.valueOf(user.getCreated()));
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    user.setId(id.getInt(1));
                }
                total = Optional.of(user);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return total;
    }

    public  Optional<User> findUserByEmail(String email, String password) {
        Optional<User> total = Optional.empty();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM users WHERE email = ? and password = ?")
        ) {
            ps.setString(1, email);
            ps.setString(2, password);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    int id = it.getInt("id");
                    String name = it.getString("name");
                    String emails = it.getString("email");
                    String passwords = it.getString("password");
                    LocalDateTime created = it.getTimestamp("created").toLocalDateTime();
                    User user = new User(id, name, emails, passwords, created);
                    return Optional.of(user);
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
      return total;
    }
}
