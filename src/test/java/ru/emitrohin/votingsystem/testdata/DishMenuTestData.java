package ru.emitrohin.votingsystem.testdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.emitrohin.votingsystem.matcher.ModelMatcher;
import ru.emitrohin.votingsystem.model.DishMenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.emitrohin.votingsystem.testdata.DishTestData.TEST_DISHES;
import static ru.emitrohin.votingsystem.testdata.MenuTestData.TEST_MENUS;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public class DishMenuTestData {

    private static final Logger LOG = LoggerFactory.getLogger(DishMenuTestData.class);

    public final static List<DishMenu> TEST_MENU_DISHES = new ArrayList<>();

    static {
        TEST_MENU_DISHES.add(new DishMenu(100023, TEST_MENUS.get(0), TEST_DISHES.get(0), 150.00));
        TEST_MENU_DISHES.add(new DishMenu(100024, TEST_MENUS.get(0), TEST_DISHES.get(1), 180.00));
        TEST_MENU_DISHES.add(new DishMenu(100025, TEST_MENUS.get(0), TEST_DISHES.get(2), 210.00));
        TEST_MENU_DISHES.add(new DishMenu(100026, TEST_MENUS.get(1), TEST_DISHES.get(4), 100.00));
        TEST_MENU_DISHES.add(new DishMenu(100027, TEST_MENUS.get(1), TEST_DISHES.get(5), 160.00));
        TEST_MENU_DISHES.add(new DishMenu(100028, TEST_MENUS.get(1), TEST_DISHES.get(6), 190.00));
        TEST_MENU_DISHES.add(new DishMenu(100029, TEST_MENUS.get(2), TEST_DISHES.get(0), 190.00));
        TEST_MENU_DISHES.add(new DishMenu(100030, TEST_MENUS.get(2), TEST_DISHES.get(1), 300.00));
        TEST_MENU_DISHES.add(new DishMenu(100031, TEST_MENUS.get(2), TEST_DISHES.get(2), 110.00));
    }

    //TODO: decide whether it is needed to compare image links

    public static final ModelMatcher<DishMenu> MATCHER = ModelMatcher.of(DishMenu.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getDish().getName(), actual.getDish().getName())
                                   /*   && Objects.equals(expected.getDish().getImageLink(), actual.getDish().getImageLink())*/
                                    && Objects.equals(expected.getMenu().getDateOfMenu(), actual.getMenu().getDateOfMenu())
                    )
    );


}
