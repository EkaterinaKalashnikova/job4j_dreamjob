package ru.job4j.dream.store;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.dream.Main;
import ru.job4j.dream.model.Candidate;
import java.time.LocalDateTime;
import java.util.List;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CandidateDbStoreTest {

    private CandidateDbStore store;

    @Before
    public  void init() {
        store = new CandidateDbStore(new Main().loadPool());
    }

    @After
    public void end() {
        List<Candidate> all = store.findAll();
        for (Candidate cdt : all) {
            this.store.delete(cdt.getId());
        }
    }

    @Test
    public void whenCreateCandidate() {
        Candidate candidate = new Candidate(0, "Name", "Description", LocalDateTime.now(), true, new byte[0]);
        store.add(candidate);
        Candidate candidateInDB = store.findById(candidate.getId());
        assertThat(candidateInDB.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        Candidate candidate = new Candidate(0, "Name", "Description", LocalDateTime.now(), true, new byte[0]);
        store.add(candidate);
        Candidate update = new Candidate(candidate.getId(), "Kat", "Description", LocalDateTime.now(), true, new byte[0]);
        store.update(update);
        Candidate candidateInDB = store.findById(candidate.getId());
        assertThat(candidateInDB.getName(), is(update.getName()));
    }

    @Test
    public void whenDeleteCandidate() {
        Candidate candidate = new Candidate(0, "Name", "Description", LocalDateTime.now(), true, new byte[0]);
        store.add(candidate);
        store.delete(candidate.getId());
        assertThat(store.findById(candidate.getId()), is(nullValue()));
    }
}