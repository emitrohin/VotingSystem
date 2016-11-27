package ru.emitrohin.votingsystem.testdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOG = LoggerFactory.getLogger(DishTestData.class);

    public final static List<Dish> TEST_DISHES = new ArrayList<>();

    static {
        TEST_DISHES.add(new Dish(100015, "Картофель, в мундире", "http://img07.rl0.ru/eda/c300x300/s2.afisha-eda.ru/Photos/120214125956-120214130233-p-O-kartofel-zapechennij-v-mundire.jpg"));
        TEST_DISHES.add(new Dish(100016, "Рататуй", "http://img04.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/150810204910-150818154229-p-O-ratatuj.jpg"));
        TEST_DISHES.add(new Dish(100017, "Борщ", "http://img08.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/120131084941-120214160730-p-O-kurica-zapechennaja-v-hrustjaschej-korochke-s-imbirem-apelsinami.jpg"));
        TEST_DISHES.add(new Dish(100018, "Гратен дофинуа", "http://img08.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/151002075307-151009125656-p-O-graten-dofinua.jpg"));
        TEST_DISHES.add(new Dish(100019, "Чахохбили из курицы", "http://img05.rl0.ru/eda/c172x172/s1.afisha-eda.ru/Photos/120213175727-120213180044-p-O-chahohbili-iz-kurici.jpg"));
        TEST_DISHES.add(new Dish(100020, "Жареный рис с яйцом по‑китайски", "http://img05.rl0.ru/eda/c172x172/s1.afisha-eda.ru/Photos/150428090447-150505141508-p-O-zharenij-ris-s-jajcom-po-kitajski.jpg"));
        TEST_DISHES.add(new Dish(100021, "Хинкали", "http://img05.rl0.ru/eda/c172x172/s2.afisha-eda.ru/Photos/120131112107-150527002516-p-O-hinkali.jpg"));
        TEST_DISHES.add(new Dish(100022, "Тикка-масала", "http://img04.rl0.ru/eda/c172x172/s1.afisha-eda.ru/Photos/120131082425-130725170057-p-O-tikka-masala.jpg"));
    }

    public static final ModelMatcher<Dish> MATCHER = ModelMatcher.of(Dish.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getName(), actual.getName())
                                    && Objects.equals(expected.getImageLink(), actual.getImageLink())
                    )
    );


}
