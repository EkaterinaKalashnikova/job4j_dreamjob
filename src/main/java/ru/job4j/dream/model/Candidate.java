package ru.job4j.dream.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

public class Candidate implements Serializable {
    private int id;
    private String name;
    private String desc;
    private LocalDateTime created;
    private boolean visible;
    private byte[] photo;

    public Candidate(int id, String name, String desc, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.desc = desc;
        this.created = created;
    }

    public Candidate(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Candidate() {

    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Candidate candidate = (Candidate) o;
        return id == candidate.id
                && Objects.equals(name, candidate.name)
                && Objects.equals(desc, candidate.desc)
                && Objects.equals(created, candidate.created)
                && Arrays.equals(photo, candidate.photo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, desc, created, photo);
    }
}
