package ru.emitrohin.votingsystem.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.emitrohin.votingsystem.TestUtil;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.service.interfaces.UserService;
import ru.emitrohin.votingsystem.util.JpaUtil;
import ru.emitrohin.votingsystem.web.json.JsonUtil;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.emitrohin.votingsystem.TestUtil.userHttpBasic;
import static ru.emitrohin.votingsystem.model.Role.ROLE_USER;
import static ru.emitrohin.votingsystem.testdata.UserTestData.*;

public class UserControllerTest extends AbstractControllerTest {

    private static final String REST_URL = UserController.CONTROLLER_URL;

    @Autowired
    protected UserService userService;

    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setUp() {
        userService.evictCache();
        jpaUtil.clear2ndLevelHibernateCache();
    }


    @Test
    public void testNotAdmin() throws Exception {
        mockMvc.perform(delete(REST_URL + 100000)
                .with(userHttpBasic(TEST_USERS.get(2))))
                .andExpect(status().isForbidden());
    }


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + 100000)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_USERS.get(0)));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + 100006)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk());
        List<User> result = new ArrayList<>(TEST_USERS);
        result.remove(result.get(6));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), userService.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }


    @Test
    public void testUpdate() throws Exception {
        User updated = TEST_USERS.get(2);
        updated.setFirstName("UpdatedNam1e");

        mockMvc.perform(put(REST_URL + "/" + TEST_USERS.get(2).getId())
                .with(userHttpBasic(TEST_USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, userService.get(100002));
        reinit();
    }

    @Test
    public void testCreate() throws Exception {
        User expected = new User(null, "Ololosha", "Password", "new@email.not", "Ololoev", "ustimov", true, EnumSet.of(ROLE_USER));
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(TEST_USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        User returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<User> result = new ArrayList<>(TEST_USERS);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), userService.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(TEST_USERS)));
    }
}