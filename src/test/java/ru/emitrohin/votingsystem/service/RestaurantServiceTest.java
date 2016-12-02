package ru.emitrohin.votingsystem.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.service.interfaces.MenuService;
import ru.emitrohin.votingsystem.service.interfaces.RestaurantService;
import ru.emitrohin.votingsystem.testdata.MenuTestData;
import ru.emitrohin.votingsystem.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.emitrohin.votingsystem.testdata.MenuTestData.TEST_MENUS;
import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.TEST_RESTAURANTS;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    protected RestaurantService service;

    @Autowired
    protected MenuService menuService;

    @Before
    public void setUp() {
        service.evictCache();
        menuService.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        Restaurant newRestaurant = new Restaurant(null, "New", "link");
        Restaurant created = service.save(newRestaurant);
        newRestaurant.setId(created.getId());
        Collection<Restaurant> result = new ArrayList<>(TEST_RESTAURANTS);
        result.add(created);
        MATCHER.assertCollectionEquals(result, service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateNameSave() throws Exception {
        service.save(new Restaurant(null, "Асеана", "Password"));
    }


    @Test
    public void testDelete() throws Exception {
        service.delete(100008);
        List<Restaurant> result = new ArrayList<>(TEST_RESTAURANTS);
        result.remove(result.get(0));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), service.getAll());
    }

    @Test
    public void testCascadeMenuDelete() throws Exception {
        service.delete(100008);
        List<Menu> result = new ArrayList<>(TEST_MENUS);
        result.remove(0);
        MenuTestData.MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), menuService.getAll());
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
        Restaurant restaurant = service.get(100008);
        MATCHER.assertEquals(TEST_RESTAURANTS.get(0), restaurant);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Restaurant> all = service.getAll();
        MATCHER.assertCollectionEquals(TEST_RESTAURANTS, all);
    }

    @Test
    public void testUpdate() throws Exception {
        Restaurant updated = new Restaurant(TEST_RESTAURANTS.get(0));
        updated.setName("UpdatedName");
        updated.setImageLink("UpdatedLink");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(100008));
    }

}