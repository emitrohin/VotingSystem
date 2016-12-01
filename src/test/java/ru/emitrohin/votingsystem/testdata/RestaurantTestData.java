package ru.emitrohin.votingsystem.testdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.emitrohin.votingsystem.matcher.ModelMatcher;
import ru.emitrohin.votingsystem.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public class RestaurantTestData {
    public final static List<Restaurant> TEST_RESTAURANTS = new ArrayList<>();
    public static final ModelMatcher<Restaurant> MATCHER = ModelMatcher.of(Restaurant.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getName(), actual.getName())
                                    && Objects.equals(expected.getImageLink(), actual.getImageLink())
                    )
    );

    private static final Logger LOG = LoggerFactory.getLogger(RestaurantTestData.class);

    static {
        TEST_RESTAURANTS.add(new Restaurant(100008, "Ривьера", "http://restoranaltona.ru/images/vip%202/IMG_9280.jpg"));
        TEST_RESTAURANTS.add(new Restaurant(100009, "Гранд каньон", "http://restoranaltona.ru/images/vip%202/IMG_9308.jpg"));
        TEST_RESTAURANTS.add(new Restaurant(100010, "Асеана", "http://restoranaltona.ru/images/vip%202/IMG_9324.jpg"));
        TEST_RESTAURANTS.add(new Restaurant(100011, "У Ашота", null));
    }
}
