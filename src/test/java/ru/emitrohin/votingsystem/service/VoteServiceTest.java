package ru.emitrohin.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.votingsystem.model.Vote;
import ru.emitrohin.votingsystem.service.interfaces.VoteService;
import ru.emitrohin.votingsystem.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.TEST_RESTAURANTS;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;
import static ru.emitrohin.votingsystem.testdata.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    protected VoteService service;

    @Test
    public void testSaveNewVoteOnTime() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 10, 0));

        Vote vote = new Vote(null, TEST_RESTAURANTS.get(2), TEST_USERS.get(6), TimeUtil.now());
        Vote created = service.vote(TEST_USERS.get(6).getId(), TEST_RESTAURANTS.get(2).getId());
        vote.setId(created.getId());
        Collection<Vote> result = new ArrayList<>(TEST_VOTES);
        result.add(created);
        MATCHER.assertCollectionEquals(result, service.getAll());
    }

    @Test
    public void testSaveVoteWithChangedRestautantOnTime() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 10, 0));

        List<Vote> result = new ArrayList<>(TEST_VOTES);
        Vote existing = result.get(result.size() - 1);
        existing.setRestaurant(TEST_RESTAURANTS.get(1));

        service.vote(TEST_USERS.get(5).getId(), TEST_RESTAURANTS.get(1).getId());
        MATCHER.assertCollectionEquals(result, service.getAll());
        reinit(); // dunno how to control data integrity when changes happens in TEST_VOTES
    }

    @Test(expected = Exception.class)
    public void testSaveNewVoteWhenVotingIsOver() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 12, 0));
        service.vote(TEST_USERS.get(6).getId(), TEST_RESTAURANTS.get(2).getId());
    }

    @Test
    public void testDuplicateSave() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 9, 0));
        service.vote(TEST_USERS.get(0).getId(), TEST_RESTAURANTS.get(1).getId());
    }

    @Test
    public void testGetAll() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 0, 0));
        Collection<Vote> all = service.getAll();
        MATCHER.assertCollectionEquals(TEST_VOTES, all);
    }


    @Test
    public void testGetAllCurrent() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 0, 0));
        Collection<Vote> all = service.getAllCurrent();
        Collection<Vote> actual = TEST_VOTES
                .stream()
                .filter(x -> x.getVoteDate().compareTo(TimeUtil.now()) == 0)
                .collect(Collectors.toList());

        MATCHER.assertCollectionEquals(actual, all);
    }


}