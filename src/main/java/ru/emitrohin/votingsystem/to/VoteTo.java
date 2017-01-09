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

    private String leaderName;
    private HashMap<String, Integer> results;

    public VoteTo() {
    }

    public VoteTo(String leaderName, HashMap<String, Integer> results) {
        this.leaderName = leaderName;
        this.results = results;
    }

    public static VoteTo parseResults(List<Vote> voteList) {
        HashMap<String, Integer> map = parseVoteList(voteList);
        String name = map.entrySet().stream().max((entry1, entry2) -> entry1.getValue() > entry2.getValue() ? 1 : -1).get().getKey();
        return new VoteTo(name, map);
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

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }


}
