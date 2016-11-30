package ru.emitrohin.votingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.votingsystem.service.interfaces.VoteService;

public class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.CONTROLLER_URL;

    @Autowired
    protected VoteService voteService;

    @Test
    public void testNewVoteVoteOnTime() throws Exception {
        System.out.println();
    }


 /*   @Test
    public void testNewVoteVoteOnTime() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 10, 0));

        Vote expected = new Vote(null, TEST_RESTAURANTS.get(2), TEST_USERS.get(7), TimeUtil.now());

        ResultActions action = mockMvc.perform(get(REST_URL + "/" + TEST_RESTAURANTS.get(2).getId()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Vote returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<Vote> result = new ArrayList<>(TEST_VOTES);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), voteService.getAll());
    }*/

  /*  @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(TEST_DISHES)));
    }*/
}