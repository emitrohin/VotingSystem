package ru.emitrohin.votingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.emitrohin.votingsystem.TestUtil;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.service.interfaces.RestaurantService;
import ru.emitrohin.votingsystem.web.json.JsonUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.emitrohin.votingsystem.TestUtil.userHttpBasic;
import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.TEST_RESTAURANTS;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;

public class RestaurantControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantController.CONTROLLER_URL;

    @Autowired
    protected RestaurantService restaurantService;

    @Test
    public void testNotAdmin() throws Exception {
        mockMvc.perform(delete(REST_URL + 100008)
                .with(userHttpBasic(TEST_USERS.get(2))))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + 100008)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_RESTAURANTS.get(0)));
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
        mockMvc.perform(delete(REST_URL + 100008)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk());
        List<Restaurant> result = new ArrayList<>(TEST_RESTAURANTS);
        result.remove(result.get(0));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), restaurantService.getAll());
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
        Restaurant updated = new Restaurant(TEST_RESTAURANTS.get(0));
        updated.setName("UpdatedName");
        mockMvc.perform(put(REST_URL + 100008)
                .with(userHttpBasic(TEST_USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, restaurantService.get(100008));
    }

    @Test
    public void testCreate() throws Exception {
        Restaurant expected = new Restaurant(null, "WowOhMyGod", "isThatLink");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(TEST_USERS.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<Restaurant> result = new ArrayList<>(TEST_RESTAURANTS);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), restaurantService.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "/all")
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(TEST_RESTAURANTS)));
    }
}