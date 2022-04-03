package ru.job4j.dream.store;

import ru.job4j.dream.model.Candidate;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CandidateStore {

    private static final CandidateStore INST = new CandidateStore();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Marina", "4 course MAI", LocalDateTime.now()));
        candidates.put(2, new Candidate(2, "Pavel", "work experience 2 years", LocalDateTime.now()));
        candidates.put(3, new Candidate(3, "Ivan", "no work experience", LocalDateTime.now()));
        candidates.put(4, new Candidate(4, "Anna", "entered the 1st year of MSTU", LocalDateTime.now()));
    }

    public static CandidateStore instOf() {
        return INST;
    }

    public Collection<Candidate> findAll() {
        return candidates.values();
    }
}
