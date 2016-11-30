package ru.emitrohin.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;


/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
@Transactional(readOnly = true)
public interface JpaVoteRepository extends Repository<Vote, Integer> {

    @Transactional
    Vote save(Vote vote);

    @Query("SELECT v FROM Vote v JOIN v.user u JOIN v.restaurant r WHERE u.id=?1 and v.voteDate =?2")
    Vote getByUserId(int userId, LocalDate date);

    List<Vote> findAll();

    @Query("SELECT v FROM Vote v JOIN v.user u JOIN v.restaurant r WHERE v.voteDate =?1")
    List<Vote> findAllCurrent(LocalDate now);
}
