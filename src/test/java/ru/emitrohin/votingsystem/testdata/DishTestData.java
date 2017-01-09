package ru.emitrohin.votingsystem.testdata;

import ru.emitrohin.votingsystem.matcher.ModelMatcher;
import ru.emitrohin.votingsystem.model.Dish;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public class DishTestData {

    public final static List<Dish> TEST_DISHES = new ArrayList<>();
    public static final ModelMatcher<Dish> MATCHER = ModelMatcher.of(Dish.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getName(), actual.getName())
                                    && Objects.equals(expected.getImageLink(), actual.getImageLink())
                                    && Objects.equals(expected.getPrice(), actual.getPrice())
                    )
    );

    static {
        TEST_DISHES.add(new Dish(100015, "Рататуй", 15000L, "1.jpg"));
        TEST_DISHES.add(new Dish(100016, "Картофель, в мундире", 18000L, "2.jpg"));
        TEST_DISHES.add(new Dish(100017, "Борщ", 21000L, "3.jpg"));
        TEST_DISHES.add(new Dish(100018, "Гратен дофинуа", 10000L, "4.jpg"));
        TEST_DISHES.add(new Dish(100019, "Чахохбили из курицы", 16000L, "5.jpg"));
        TEST_DISHES.add(new Dish(100020, "Жареный рис с яйцом", 19000L, "6.jpg"));
        TEST_DISHES.add(new Dish(100021, "Хинкали", 19000L, "7.jpg"));
        TEST_DISHES.add(new Dish(100022, "Тикка-масала", 30000L, "8.jpg"));
        TEST_DISHES.add(new Dish(100023, "НЕ что", 11000L, null));
    }


}
