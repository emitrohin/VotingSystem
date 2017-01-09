package ru.emitrohin.votingsystem.service;

import ru.emitrohin.votingsystem.model.Vote;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   25.11.2016.
 */
public interface VoteService {
    Vote vote(int userId, int restaurantId);

    List<Vote> getAll();

    List<Vote> getAllCurrent();
}