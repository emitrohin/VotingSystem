package ru.emitrohin.votingsystem.service;

import ru.emitrohin.votingsystem.model.Menu;

import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface MenuService extends AbstractService<Menu> {

    Menu save(int restaurantID);

    Menu getByRestaurantId(int restaurantId);

    List<Menu> getAllCurrent();
}
