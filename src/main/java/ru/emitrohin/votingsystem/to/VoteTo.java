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

    private String winnerName;
    private HashMap<String, Integer> results;
    private boolean votingIsOver;

    public VoteTo() {
    }

    public VoteTo(String winnerName, HashMap<String, Integer> results, boolean votingIsOver) {
        this.winnerName = winnerName;
        this.results = results;
        this.votingIsOver = votingIsOver;
    }

    public static VoteTo parseSubTotals(List<Vote> voteList) {
        return new VoteTo("Name will be available after 11:00", parseVoteList(voteList), false);
    }

    public static VoteTo parseResults(List<Vote> voteList) {
        HashMap<String, Integer> map = parseVoteList(voteList);
        String name = map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        return new VoteTo(name, map, true);
    }

    public static HashMap<String, Integer> parseVoteList(List<Vote> voteList) {
        HashMap<String, Integer> map = new HashMap<>();
        for (Vote v : voteList) {
            Integer count = map.get(v.getRestaurant().getName());
            if (count == null) {
                map.put(v.getRestaurant().getName(), 1);
            } else {
                map.put(v.getRestaurant().getName(), count + 1);
            }
        }
        return map;
    }

    public Map<String, Integer> getResults() {
        return results;
    }

    public void setResults(HashMap<String, Integer> results) {
        this.results = results;
    }

    public boolean isVotingIsOver() {
        return votingIsOver;
    }

    public void setVotingIsOver(boolean votingIsOver) {
        this.votingIsOver = votingIsOver;
    }

    public String getWinnerName() {
        return winnerName;
    }

    public void setWinnerName(String winnerName) {
        this.winnerName = winnerName;
    }


}
