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

    private int winnerId;
    private Map<String, Integer> results;
    private boolean votingIsOver;

    public VoteTo(int winnerId, Map<String, Integer> results, boolean votingIsOver) {
        this.winnerId = winnerId;
        this.results = results;
        this.votingIsOver = votingIsOver;
    }

    public static VoteTo parseSubTotals(List<Vote> voteList) {
        return new VoteTo(-1, parseVoteList(voteList), false);
    }

    public static VoteTo parseResults(List<Vote> voteList) {
        Map<String, Integer> map = parseVoteList(voteList);
        int winnerId = map.values().stream().max(Integer::compareTo).orElse(0);
        return new VoteTo(winnerId, map, true);
    }

    public static Map<String, Integer> parseVoteList(List<Vote> voteList) {
        Map<String, Integer> map = new HashMap<>();
        for (Vote v : voteList) {
            if (map.containsKey(v.getRestaurant().getName()))
                map.put(v.getRestaurant().getName(), 1);
            else
                map.put(v.getRestaurant().getName(), map.get(v.getRestaurant().getName()) + 1);
        }
        return map;
    }

    public Map<String, Integer> getResults() {
        return results;
    }

    public void setResults(Map<String, Integer> results) {
        this.results = results;
    }

    public boolean isVotingIsOver() {
        return votingIsOver;
    }

    public void setVotingIsOver(boolean votingIsOver) {
        this.votingIsOver = votingIsOver;
    }

    public int getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(int winnerId) {
        this.winnerId = winnerId;
    }


}
