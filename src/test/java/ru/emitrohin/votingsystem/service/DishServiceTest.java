package ru.emitrohin.votingsystem.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.service.interfaces.DishService;
import ru.emitrohin.votingsystem.service.interfaces.MenuService;
import ru.emitrohin.votingsystem.util.exception.NotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static ru.emitrohin.votingsystem.testdata.DishTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.DishTestData.TEST_DISHES;

public class DishServiceTest extends AbstractServiceTest {

    @Autowired
    protected DishService service;

    @Autowired
    protected MenuService menuService;


    @Before
    public void setUp() {
        service.evictCache();
        menuService.evictCache();
    }

    @Test
    public void testSave() throws Exception {
        Dish newDish = new Dish(null, "New", "link");
        Dish created = service.save(newDish);
        newDish.setId(created.getId());
        Collection<Dish> result = new ArrayList<>(TEST_DISHES);
        result.add(created);
        MATCHER.assertCollectionEquals(result, service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateNameSave() throws Exception {
        service.save(new Dish(null, "Рататуй", "link"));
    }


    @Test
    public void testDelete() throws Exception {
        service.delete(100016);
        List<Dish> result = new ArrayList<>(TEST_DISHES);
        result.remove(result.get(1));
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
        Dish dish = service.get(100022);
        MATCHER.assertEquals(TEST_DISHES.get(TEST_DISHES.size() - 1), dish);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<Dish> all = service.getAll();
        MATCHER.assertCollectionEquals(TEST_DISHES, all);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = new Dish(TEST_DISHES.get(TEST_DISHES.size() - 1));
        updated.setName("UpdatedName");
        updated.setImageLink("UpdatedLink");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(100022));
    }

}