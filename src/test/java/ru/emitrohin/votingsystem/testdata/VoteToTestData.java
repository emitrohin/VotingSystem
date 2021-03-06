package ru.emitrohin.votingsystem.testdata;

import ru.emitrohin.votingsystem.matcher.ModelMatcher;
import ru.emitrohin.votingsystem.to.VoteTo;

import java.util.HashMap;
import java.util.Objects;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public class VoteToTestData {

    public static final ModelMatcher<VoteTo> MATCHER = ModelMatcher.of(VoteTo.class,
            (expected, actual) -> expected == actual || Objects.equals(expected.getLeaderName(), actual.getLeaderName())
    );

    private static HashMap<String, Integer> results = new HashMap<>();
    public static VoteTo TEST_VOTES = new VoteTo("Гранд каньон", results);

    static {
        reinit();
    }

    public static void reinit() {
        results.clear();
        results.put("Гранд каньон", 3);
        results.put("Ривьера", 2);
        results.put("Асеана", 1);
    }
}
