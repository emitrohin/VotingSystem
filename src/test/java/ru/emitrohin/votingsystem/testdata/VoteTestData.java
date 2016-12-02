package ru.emitrohin.votingsystem.testdata;

import ru.emitrohin.votingsystem.matcher.ModelMatcher;
import ru.emitrohin.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.TEST_RESTAURANTS;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public class VoteTestData {

    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getRestaurant().getId(), actual.getRestaurant().getId())
                                    && Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getVoteDate(), actual.getVoteDate())
                    )
    );

    public static List<Vote> TEST_VOTES = new ArrayList<>();

    static {
        reinit();
    }

    public static void reinit() {
            TEST_VOTES.clear();
            TEST_VOTES.add(new Vote(100032, TEST_RESTAURANTS.get(1), TEST_USERS.get(0), LocalDate.of(2016, 11, 26)));
            TEST_VOTES.add(new Vote(100033, TEST_RESTAURANTS.get(1), TEST_USERS.get(1), LocalDate.of(2016, 11, 26)));
            TEST_VOTES.add(new Vote(100034, TEST_RESTAURANTS.get(1), TEST_USERS.get(2), LocalDate.of(2016, 11, 26)));
            TEST_VOTES.add(new Vote(100035, TEST_RESTAURANTS.get(0), TEST_USERS.get(3), LocalDate.of(2016, 11, 26)));
            TEST_VOTES.add(new Vote(100036, TEST_RESTAURANTS.get(0), TEST_USERS.get(4), LocalDate.of(2016, 11, 26)));
            TEST_VOTES.add(new Vote(100037, TEST_RESTAURANTS.get(2), TEST_USERS.get(5), LocalDate.of(2016, 11, 26)));
    }

}
