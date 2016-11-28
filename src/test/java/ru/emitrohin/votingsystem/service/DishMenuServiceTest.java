package ru.emitrohin.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.emitrohin.votingsystem.model.DishMenu;
import ru.emitrohin.votingsystem.service.interfaces.DishMenuService;
import ru.emitrohin.votingsystem.to.DishMenuTo;
import ru.emitrohin.votingsystem.util.DishMenuUtil;
import ru.emitrohin.votingsystem.util.TimeUtil;
import ru.emitrohin.votingsystem.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static ru.emitrohin.votingsystem.testdata.DishMenuTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.DishMenuTestData.TEST_MENU_DISHES;
import static ru.emitrohin.votingsystem.testdata.DishTestData.TEST_DISHES;
import static ru.emitrohin.votingsystem.testdata.MenuTestData.TEST_MENUS;

public class DishMenuServiceTest extends AbstractServiceTest {

    @Autowired
    protected DishMenuService service;

    @Test(expected = UnsupportedOperationException.class)
    public void testSaveUnsupportedOperation() throws Exception {
        DishMenu newDishMenu = new DishMenu(null, TEST_MENUS.get(2), TEST_DISHES.get(3), 100.00);
        DishMenu created = service.save(newDishMenu);
    }

    @Test
    public void testSaveFromTo() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 0, 0));

        DishMenuTo dishMenuTo = DishMenuUtil.asTo(
                new DishMenu(null, TEST_MENUS.get(2), TEST_DISHES.get(3), 100.00));
        DishMenu created = service.save(dishMenuTo);
        dishMenuTo.setId(created.getId());
        Collection<DishMenu> result = new ArrayList<>(TEST_MENU_DISHES);
        result.add(created);
        MATCHER.assertCollectionEquals(result, service.getAll());
    }

    @Test(expected = Exception.class)
    public void testSaveFromToInvalidDate() throws Exception {
        DishMenuTo dishMenuTo = DishMenuUtil.asTo(
                new DishMenu(null, TEST_MENUS.get(2), TEST_DISHES.get(3), 100.00));
        DishMenu created = service.save(dishMenuTo);
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateNameSave() throws Exception {
        TimeUtil.useFixedClockAt(LocalDateTime.of(2016, 11, 26, 0, 0));

        DishMenuTo dishMenuTo = DishMenuUtil.asTo(
                new DishMenu(null, TEST_MENUS.get(0), TEST_DISHES.get(0), 100.00));
        service.save(dishMenuTo);
    }


    @Test
    public void testDelete() throws Exception {
        service.delete(100030);
        List<DishMenu> result = new ArrayList<>(TEST_MENU_DISHES);
        result.remove(result.get(result.size() - 2));
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
        DishMenu dishMenu = service.get(100031);
        MATCHER.assertEquals(TEST_MENU_DISHES.get(TEST_MENU_DISHES.size() - 1), dishMenu);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<DishMenu> all = service.getAll();
        MATCHER.assertCollectionEquals(TEST_MENU_DISHES, all);
    }

    @Test
    public void testGetAllByMenuId() throws Exception {
        Collection<DishMenu> all = service.findAllByMenuId(TEST_MENUS.get(2).getId());
        List<DishMenu> result = new ArrayList<>(TEST_MENU_DISHES
                .stream()
                .filter(x -> x.getMenu().getId() == TEST_MENUS.get(2).getId())
                .collect(Collectors.toList()));
        MATCHER.assertCollectionEquals(result, all);
    }

    @Test
    public void testUpdate() throws Exception {
        DishMenuTo updated = DishMenuUtil.asTo(TEST_MENU_DISHES.get(0));
        updated.setPrice(200.00);
        service.update(updated);
        MATCHER.assertEquals(DishMenuUtil.createNewFromTo(updated), service.get(100023));
    }

}