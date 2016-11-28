package ru.emitrohin.votingsystem.to;

import ru.emitrohin.votingsystem.model.Vote;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: E_Mitrohin
 * Date:   28.11.2016.
 */
public class VoteTo {

    private Map<Integer, Integer> results;
    private boolean votingIsOver;

    public VoteTo(Map<Integer, Integer> results, boolean votingIsOver) {
        this.results = results;
        this.votingIsOver = votingIsOver;
    }

    public static Map<Integer, Integer> parseResults(List<Vote> voteList) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Vote v : voteList) {
            if (map.containsKey(v.getRestaurant().getId()))
                map.put(v.getRestaurant().getId(), 1);
            else
                map.put(v.getRestaurant().getId(), map.get(v.getRestaurant().getId()) + 1);
        }
        return map;
    }

    public Map<Integer, Integer> getResults() {
        return results;
    }

    public void setResults(Map<Integer, Integer> results) {
        this.results = results;
    }

    public boolean isVotingIsOver() {
        return votingIsOver;
    }

    public void setVotingIsOver(boolean votingIsOver) {
        this.votingIsOver = votingIsOver;
    }
}
