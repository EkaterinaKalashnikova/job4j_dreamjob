package ru.job4j.dream.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.CandidateDbStore;
import java.util.List;

@Service
@ThreadSafe
public class CandidateService {

    private final CandidateDbStore store;

    public CandidateService(CandidateDbStore store) {
        this.store = store;
    }

    public List<Candidate> findAll() {
        return store.findAll();
    }

    public void add(Candidate candidate) {
         store.add(candidate);
    }

    public void update(Candidate candidate) {
        store.update(candidate);
    }

    public Candidate findById(int id) {
        return store.findById(id);
    }

    public boolean delete(int id) {
        return store.delete(id);
    }

    public Candidate getById(int id) {
        return store.findById(id);
    }
}
