package ru.emitrohin.votingsystem.service.interfaces;

import ru.emitrohin.votingsystem.model.Menu;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface MenuService extends AbstractService<Menu> {

    Menu save(Menu item, int restaurantID);
}
