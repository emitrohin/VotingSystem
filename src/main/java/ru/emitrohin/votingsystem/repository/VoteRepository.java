package ru.emitrohin.votingsystem.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface VoteRepository extends Repository<Vote, Integer> {

    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v JOIN v.user u JOIN v.restaurant r WHERE u.id=?1 and v.voteTimestamp =?2")
    Vote getByUserId(int userId, LocalDate date);

    List<Vote> findAll();

    @Query("SELECT v FROM Vote v JOIN v.user u JOIN v.restaurant r WHERE v.voteTimestamp =?1")
    List<Vote> findAllCurrent(LocalDate now);
}
