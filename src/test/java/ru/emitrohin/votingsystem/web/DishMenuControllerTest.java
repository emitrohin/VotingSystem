package ru.emitrohin.votingsystem.web;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.emitrohin.votingsystem.TestUtil;
import ru.emitrohin.votingsystem.model.DishMenu;
import ru.emitrohin.votingsystem.service.interfaces.DishMenuService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.emitrohin.votingsystem.TestUtil.userHttpBasic;
import static ru.emitrohin.votingsystem.testdata.DishMenuTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.DishMenuTestData.TEST_MENU_DISHES;
import static ru.emitrohin.votingsystem.testdata.MenuTestData.TEST_MENUS;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;

public class DishMenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishMenuController.CONTROLLER_URL;

    @Autowired
    protected DishMenuService service;

    @Test
    public void testNotAdmin() throws Exception {
        mockMvc.perform(get(REST_URL + 100023)
                .with(userHttpBasic(TEST_USERS.get(2))))
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + 100023)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(TEST_MENU_DISHES.get(0)));
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
        mockMvc.perform(delete(REST_URL + 100030)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andDo(print())
                .andExpect(status().isOk());
        List<DishMenu> result = new ArrayList<>(TEST_MENU_DISHES);
        result.remove(result.get(7));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), service.getAll());
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    /*  @Test
      public void testCreate() throws Exception {
          Menu expected = new Menu(null, LocalDate.of(2016, 11, 26));

          ResultActions action = mockMvc.perform(post(REST_URL + "/restaurant/" + TEST_RESTAURANTS.get(3).getId())
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
  */
    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(TEST_MENU_DISHES)));
    }

    @Test
    public void testGetAllByMenuId() throws Exception {
        TestUtil.print(mockMvc.perform(get(REST_URL + "/menu/" + TEST_MENUS.get(0).getId())
                .with(userHttpBasic(TEST_USERS.get(0))))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(TEST_MENU_DISHES.stream()
                        .filter(x -> x.getMenu().getId() == TEST_MENUS.get(0).getId())
                        .collect(Collectors.toList()))));
    }
}