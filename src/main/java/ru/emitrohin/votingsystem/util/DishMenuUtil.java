package ru.emitrohin.votingsystem.util;

import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.model.DishMenu;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.to.DishMenuTo;

/**
 * @author emitrohin
 * @version 1.0
 *          27.11.2016
 */
public class DishMenuUtil {

    public static DishMenu createNewFromTo(DishMenuTo to) {
        Menu menu = new Menu(to.getMenuId(), to.getDateOfMenu());
        Dish dish = new Dish(to.getDishId(), to.getName(), null);
        return new DishMenu(to.getId(), menu, dish, to.getPrice());
    }

    public static DishMenuTo asTo(DishMenu dishMenu) {
        return new DishMenuTo(dishMenu.getId(),
                dishMenu.getMenu().getId(),
                dishMenu.getDish().getId(),
                dishMenu.getMenu().getDateOfMenu(),
                dishMenu.getDish().getName(),
                dishMenu.getPrice());
    }

    public static DishMenu updateFromTo(DishMenu dishMenu, DishMenuTo dishMenuTo) {
        dishMenu.setPrice(dishMenuTo.getPrice());
        return dishMenu;
    }

}
