package ru.emitrohin.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.service.interfaces.MenuService;
import ru.emitrohin.votingsystem.testdata.RestaurantTestData;
import ru.emitrohin.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.emitrohin.votingsystem.testdata.MenuTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.MenuTestData.TEST_MENUS;
import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.TEST_RESTAURANTS;

public class MenuServiceTest extends AbstractServiceTest {

    //TODO: тест меню на дату

    @Autowired
    protected MenuService service;

    @Test
    public void testSave() throws Exception {
        Menu newMenu = new Menu(null, LocalDate.now());
        Menu created = service.save(newMenu, TEST_RESTAURANTS.get(3).getId());
        newMenu.setId(created.getId());
        List<Menu> result = new ArrayList<>(TEST_MENUS);
        result.add(created);
        MATCHER.assertCollectionEquals(result, service.getAll());
        RestaurantTestData.MATCHER.assertEquals(result.get(3).getRestaurant(), created.getRestaurant());
    }

    //Maybe
    @Test(expected = DataAccessException.class)
    public void testDuplicateIdAndDateSave() throws Exception {
        service.save(new Menu(null, LocalDate.of(2016, 11, 26)), 100009);
        System.out.println(service.getAll().size());
    }


    @Test
    public void testDelete() throws Exception {
        service.delete(100014);
        List<Menu> result = new ArrayList<>(TEST_MENUS);
        result.remove(result.get(2));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }


    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGet() throws Exception {
        Menu menu = service.get(100013);
        MATCHER.assertEquals(TEST_MENUS.get(1), menu);
        RestaurantTestData.MATCHER.assertEquals(TEST_MENUS.get(1).getRestaurant(), menu.getRestaurant());
    }


    @Test
    public void testGetAll() throws Exception {
        Collection<Menu> all = service.getAll();
        MATCHER.assertCollectionEquals(TEST_MENUS, all);
        //TODO: проверить в содержимом рестораны
    }

//TODO: прикрутить еду?
  /*  @Test
    public void testUpdate() throws Exception {
        Menu updated = new Restaurant(TEST_RESTAURANTS.get(0));
        updated.setName("UpdatedName");
        updated.setImageLink("UpdatedLink");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(100008));
    }*/

}