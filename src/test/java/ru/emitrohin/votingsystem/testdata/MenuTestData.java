package ru.emitrohin.votingsystem.testdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.emitrohin.votingsystem.matcher.ModelMatcher;
import ru.emitrohin.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.emitrohin.votingsystem.testdata.RestaurantTestData.TEST_RESTAURANTS;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public class MenuTestData {

    public final static List<Menu> TEST_MENUS = new ArrayList<>();
    public static final ModelMatcher<Menu> MATCHER = ModelMatcher.of(Menu.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId()) &&
                                    Objects.equals(expected.getDateOfMenu(), actual.getDateOfMenu())
                    )

    );
    private static final Logger LOG = LoggerFactory.getLogger(MenuTestData.class);

    static {
        TEST_MENUS.add(new Menu(100012, LocalDate.of(2016, 11, 26)));
        TEST_MENUS.add(new Menu(100013, LocalDate.of(2016, 11, 26)));
        TEST_MENUS.add(new Menu(100014, LocalDate.of(2016, 11, 26)));
        TEST_MENUS.get(0).setRestaurant(TEST_RESTAURANTS.get(0));
        TEST_MENUS.get(1).setRestaurant(TEST_RESTAURANTS.get(1));
        TEST_MENUS.get(2).setRestaurant(TEST_RESTAURANTS.get(2));
    }


}
