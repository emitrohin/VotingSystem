package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.emitrohin.votingsystem.model.Vote;
import ru.emitrohin.votingsystem.repository.datajpa.JpaVoteRepository;
import ru.emitrohin.votingsystem.repository.interfaces.VoteRepository;
import ru.emitrohin.votingsystem.util.TimeUtil;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Repository
public class VoteRepositoryImpl implements VoteRepository {

    private JpaVoteRepository repository;

    @Autowired
    public VoteRepositoryImpl(JpaVoteRepository repository) {
        this.repository = repository;
    }

    @Override
    public Vote save(Vote vote) {
        return repository.save(vote);
    }

    @Override
    public Vote getByUserId(int userId) {
        return repository.getByUserId(userId, TimeUtil.now());
    }

    @Override
    public List<Vote> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Vote> getAllCurrent(LocalDate now) {
        return repository.findAllCurrent(now);
    }
}
