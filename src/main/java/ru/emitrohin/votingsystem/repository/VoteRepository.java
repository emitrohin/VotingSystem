package ru.emitrohin.votingsystem.repository;

import ru.emitrohin.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface VoteRepository {

    Vote save(Vote vote);

    Vote getByUserId(int userId);

    List<Vote> getAll();

    List<Vote> getAllCurrent(LocalDate now);
}
