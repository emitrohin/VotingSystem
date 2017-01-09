package ru.emitrohin.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.emitrohin.votingsystem.testdata.DishTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.DishTestData.TEST_DISHES;
import static ru.emitrohin.votingsystem.testdata.MenuTestData.TEST_MENUS;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    protected DishService dishService;

    @Autowired
    protected MenuService menuService;

    @Test
    public void testSave() throws Exception {
        Dish newDish = new Dish(null, "New", 10000L, "link");
        Dish created = dishService.save(newDish, 100012);
        newDish.setId(created.getId());
        Collection<Dish> result = new ArrayList<>(TEST_DISHES);
        result.add(created);
        MATCHER.assertCollectionEquals(result, dishService.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateNameSave() throws Exception {
        dishService.save(new Dish(null, "Рататуй", 10000L, "link"), 100012);
    }


    @Test
    public void testDelete() throws Exception {
        dishService.delete(100016);
        List<Dish> result = new ArrayList<>(TEST_DISHES);
        result.remove(result.get(1));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), dishService.getAll());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testNotFoundDelete() throws Exception {
        dishService.delete(1);
    }


    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        dishService.get(1);
    }

    @Test
    public void testGet() throws Exception {
        Dish dish = dishService.get(100023);
        MATCHER.assertEquals(TEST_DISHES.get(TEST_DISHES.size() - 1), dish);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Dish> all = dishService.getAll();
        MATCHER.assertCollectionEquals(TEST_DISHES, all);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = new Dish(TEST_DISHES.get(TEST_DISHES.size() - 1));
        updated.setMenu(TEST_MENUS.get(0));
        updated.setName("UpdatedName");
        updated.setImageLink("UpdatedLink");
        dishService.update(updated);
        MATCHER.assertEquals(updated, dishService.get(100023));
    }

}