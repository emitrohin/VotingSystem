package ru.emitrohin.votingsystem.web;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.emitrohin.votingsystem.TestUtil;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.service.interfaces.MenuService;
import ru.emitrohin.votingsystem.util.TimeUtil;
import ru.emitrohin.votingsystem.web.json.JsonUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.emitrohin.votingsystem.TestUtil.userHttpBasic;
import static ru.emitrohin.votingsystem.testdata.MenuTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.MenuTestData.TEST_MENUS;
import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.TEST_RESTAURANTS;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;

public class MenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuController.CONTROLLER_URL;

    @Autowired
    protected MenuService menuService;

    @Before
    public void setUp() {
        menuService.evictCache();
    }

    @Test
    public void testNotAdmin() throws Exception {
        mockMvc.perform(delete(REST_URL + 100012)
                .with(userHttpBasic(TEST_USERS.get(2))))
                .andExpect(status().isForbidden());
    }


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + 100012)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_MENUS.get(0)));
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
        mockMvc.perform(delete(REST_URL + 100012)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk());
        List<Menu> result = new ArrayList<>(TEST_MENUS);
        result.remove(result.get(0));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), menuService.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        Menu expected = new Menu(null, LocalDate.of(2016, 11, 26));

        ResultActions action = mockMvc.perform(post(REST_URL + "/restaurant/" + TEST_RESTAURANTS.get(3).getId())
                .with(userHttpBasic(TEST_USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        expected.setRestaurant(TEST_RESTAURANTS.get(3));
        Menu returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<Menu> result = new ArrayList<>(TEST_MENUS);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), menuService.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 10, 0));

        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(TEST_MENUS)));
    }
}