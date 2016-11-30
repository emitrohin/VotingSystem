package ru.emitrohin.votingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.emitrohin.votingsystem.model.Vote;
import ru.emitrohin.votingsystem.service.interfaces.VoteService;
import ru.emitrohin.votingsystem.util.TimeUtil;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.emitrohin.votingsystem.TestUtil.userHttpBasic;
import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.TEST_RESTAURANTS;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;
import static ru.emitrohin.votingsystem.testdata.VoteTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.VoteTestData.TEST_VOTES;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.CONTROLLER_URL;

    @Autowired
    protected VoteService voteService;


    @Test
    public void testNewVoteVoteOnTime() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 10, 0));

        Vote expected = new Vote(null, TEST_RESTAURANTS.get(2), TEST_USERS.get(7), TimeUtil.now());

        ResultActions action = mockMvc.perform(get(REST_URL + "/" + TEST_RESTAURANTS.get(2).getId())
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isCreated());

        Vote returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<Vote> result = new ArrayList<>(TEST_VOTES);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), voteService.getAll());
    }

  /*  @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(TEST_DISHES)));
    }*/
}