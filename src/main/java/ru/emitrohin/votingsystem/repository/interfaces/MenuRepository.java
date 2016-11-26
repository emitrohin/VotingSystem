package ru.emitrohin.votingsystem.repository.interfaces;

import ru.emitrohin.votingsystem.model.Menu;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface MenuRepository extends AbstractRepository<Menu> {
    Menu save(Menu menu, int restaurantId);
}
