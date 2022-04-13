package ru.job4j.dream.service;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.CandidateStore;

import java.util.Collection;

public class CandidateService {
    private final CandidateStore store;

    private CandidateService(CandidateStore store) {
        this.store = store;
    }

    public Collection<Candidate> findAll() {
        return store.findAll();
    }

    public Candidate add(Candidate candidate) {
        return store.add(candidate);
    }

    public Candidate create(Candidate candidate) {
        return store.create(candidate);
    }

    public boolean update(Candidate candidate) {
        return store.update(candidate);
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }
}
