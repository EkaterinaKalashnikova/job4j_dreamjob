package ru.job4j.dream.store;

import org.springframework.stereotype.Repository;
import ru.job4j.dream.model.Candidate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CandidateStore {

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private static final AtomicInteger ID = new AtomicInteger(5);

    private CandidateStore() {
        candidates.put(1, new Candidate(1, "Marina", "4 course MAI", LocalDateTime.now()));
        candidates.put(2, new Candidate(2, "Pavel", "work experience 2 years", LocalDateTime.now()));
        candidates.put(3, new Candidate(3, "Ivan", "no work experience", LocalDateTime.now()));
        candidates.put(4, new Candidate(4, "Anna", "entered the 1st year of MSTU", LocalDateTime.now()));
    }

    public synchronized Collection<Candidate> findAll() {
        return candidates.values();
    }

    public synchronized Candidate add(Candidate candidate) {
        candidate.setId(ID.getAndIncrement());
        return candidates.putIfAbsent(candidate.getId(), candidate);
    }

    public synchronized boolean update(Candidate candidate) {
        boolean flag = false;
        Candidate cdt = findById(candidate.getId());
        if (cdt != null) {
            candidates.replace(candidate.getId(), candidate);
            flag = true;
        }
        return flag;
    }

    public synchronized Candidate create(Candidate candidate) {
        Candidate cdt = findById(candidate.getId());
        if (cdt == null) {
            candidates.computeIfAbsent(ID.getAndIncrement(), v -> {
                candidate.setId(v);
                return candidate;
            });
            return candidate;
        }
        return null;
    }

    public synchronized Candidate findById(int id) {
        return candidates.get(id);
    }

    public synchronized boolean delete(int id) {
        if (findById(id) != null) {
            candidates.remove(id);
            return true;
        }
        return false;
    }
}
