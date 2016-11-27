package ru.emitrohin.votingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.emitrohin.votingsystem.TestUtil;
import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.service.interfaces.DishService;
import ru.emitrohin.votingsystem.web.json.JsonUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.emitrohin.votingsystem.testdata.DishTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.DishTestData.TEST_DISHES;

public class DishControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishController.CONTROLLER_URL;

    @Autowired
    protected DishService service;

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + 100015)
               /* .with(userHttpBasic(TEST_USERS.get(0)))*/)
                .andDo(print())
                .andExpect(status().isOk())
// https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_DISHES.get(0)));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + 1))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }


    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + 100016))
                .andDo(print())
                .andExpect(status().isOk());
        List<Dish> result = new ArrayList<>(TEST_DISHES);
        result.remove(result.get(1));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), service.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testCreate() throws Exception {
        Dish expected = new Dish(null, "Шаурма", "http://yandex.ru/");

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Dish returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        Collection<Dish> result = new ArrayList<>(TEST_DISHES);
        result.add(expected);

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Collections.unmodifiableCollection(result), service.getAll());
    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(TEST_DISHES)));
    }
}