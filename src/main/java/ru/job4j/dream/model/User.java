package ru.job4j.dream.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime created;

    public User(int id, String name, String email, String password, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.created = created;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return getId() == user.getId()
                && Objects.equals(getName(), user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}
