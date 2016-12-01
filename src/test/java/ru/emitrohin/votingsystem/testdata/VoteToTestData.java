package ru.emitrohin.votingsystem.testdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getWinnerName(), actual.getWinnerName())
                                    && Objects.equals(expected.isVotingIsOver(), actual.isVotingIsOver())
                    )
    );
    private static final Logger LOG = LoggerFactory.getLogger(VoteToTestData.class);

    private static HashMap<String, Integer> results = new HashMap<>();
    public static VoteTo TEST_VOTE_TO_BEFORE_11_00 = new VoteTo("Name will be available after 11:00", results, false);
    public static VoteTo TEST_VOTE_TO_AFTER_11_00 = new VoteTo("Гранд каньон", results, true);

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
