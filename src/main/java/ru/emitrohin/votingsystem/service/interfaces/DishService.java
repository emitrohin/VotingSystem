package ru.emitrohin.votingsystem.service.interfaces;

import ru.emitrohin.votingsystem.model.Dish;

/**
 * Author: E_Mitrohin
 * Date:   25.11.2016.
 */
public interface DishService extends AbstractService<Dish> {

    Dish saveToMenu(Dish item, int menuId);

}
