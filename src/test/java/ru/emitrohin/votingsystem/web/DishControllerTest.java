package ru.emitrohin.votingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.emitrohin.votingsystem.TestUtil;
import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.service.DishService;
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
import static ru.emitrohin.votingsystem.testdata.DishTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.DishTestData.TEST_DISHES;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;

public class DishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishController.CONTROLLER_URL;

    @Autowired
    protected DishService dishService;

    @Test
    public void testNotAdmin() throws Exception {
        mockMvc.perform(get(REST_URL + 100015)
                .with(userHttpBasic(TEST_USERS.get(2))))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + 100012)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_DISHES.get(0)));
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
        mockMvc.perform(delete(REST_URL + 100016)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk());
        List<Dish> result = new ArrayList<>(TEST_DISHES);
        result.remove(result.get(1));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), dishService.getAll());
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
        Dish expected = new Dish(null, "Шаурма", 10000L, "http://yandex.ru/");

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isCreated());

        Dish returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<Dish> result = new ArrayList<>(TEST_DISHES);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), dishService.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(TEST_DISHES)));
    }

    @Test
    public void testUnAuth() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)))
                .andExpect(status().isUnauthorized());
    }
}