package ru.emitrohin.votingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.emitrohin.votingsystem.model.Vote;
import ru.emitrohin.votingsystem.service.VoteService;
import ru.emitrohin.votingsystem.testdata.VoteToTestData;
import ru.emitrohin.votingsystem.to.VoteTo;
import ru.emitrohin.votingsystem.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.emitrohin.votingsystem.TestUtil.userHttpBasic;
import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.TEST_RESTAURANTS;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;
import static ru.emitrohin.votingsystem.testdata.VoteTestData.*;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RootController.REST_URL;

    @Autowired
    protected VoteService voteService;


    @Test
    public void testUnAuth() throws Exception {
        mockMvc.perform(post(REST_URL + "/restaurants/" + TEST_RESTAURANTS.get(2).getId() + "/vote")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testNewVoteVoteBefore1100() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 10, 0));

        Vote expected = new Vote(null, TEST_RESTAURANTS.get(2), TEST_USERS.get(7), TimeUtil.now());

        ResultActions action = mockMvc.perform(post(REST_URL + "/restaurants/" + TEST_RESTAURANTS.get(2).getId() + "/vote")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(TEST_USERS.get(7))))
                .andExpect(status().isCreated());

        Vote returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<Vote> result = new ArrayList<>(TEST_VOTES);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), voteService.getAll());
    }

    @Test
    public void testNewVoteVoteAfter1100() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 22, 0));

        Vote expected = new Vote(null, TEST_RESTAURANTS.get(2), TEST_USERS.get(7), TimeUtil.now());

        ResultActions action = mockMvc.perform(post(REST_URL + "/restaurants/" + TEST_RESTAURANTS.get(2).getId() + "/vote")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(TEST_USERS.get(7))))
                .andExpect(status().isCreated());

        Vote returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<Vote> result = new ArrayList<>(TEST_VOTES);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), voteService.getAll());
    }

    @Test
    public void testVoteWithChangedRestaurantOnTime() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 9, 0));

        Vote expected = TEST_VOTES.get(5);
        expected.setRestaurant(TEST_RESTAURANTS.get(1));

        ResultActions action = mockMvc.perform(post(REST_URL + "/restaurants/" + TEST_RESTAURANTS.get(1).getId() + "/vote")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(TEST_USERS.get(5))))
                .andExpect(status().isCreated());

        Vote returned = MATCHER.fromJsonAction(action);
        Collection<Vote> result = new ArrayList<>(TEST_VOTES);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), voteService.getAll());
        reinit();
    }

    @Test
    public void testGetResults() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 12, 0));

        ResultActions action = mockMvc.perform(get(REST_URL + "/restaurants/votes")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(TEST_USERS.get(5))))
                .andExpect(status().isOk());

        VoteTo returned = VoteToTestData.MATCHER.fromJsonAction(action);

        VoteToTestData.MATCHER.assertEquals(VoteToTestData.TEST_VOTES, returned);
    }

    @Test
    public void testVoteAndRevoteWithResults() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 10, 0));

        Vote expected = new Vote(null, TEST_RESTAURANTS.get(2), TEST_USERS.get(7), TimeUtil.now());

        ResultActions action = mockMvc.perform(post(REST_URL + "/restaurants/" + TEST_RESTAURANTS.get(2).getId() + "/vote")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(TEST_USERS.get(7))))
                .andExpect(status().isCreated());

        Vote returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<Vote> result = new ArrayList<>(TEST_VOTES);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), voteService.getAll());

        expected.setRestaurant(TEST_RESTAURANTS.get(0));

        action = mockMvc.perform(post(REST_URL + "/restaurants/" + TEST_RESTAURANTS.get(0).getId() + "/vote")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(TEST_USERS.get(7))))
                .andExpect(status().isCreated());

        returned = MATCHER.fromJsonAction(action);
        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), voteService.getAll());
        reinit();
    }
}