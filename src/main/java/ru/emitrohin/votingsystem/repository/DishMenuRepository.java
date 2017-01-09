package ru.emitrohin.votingsystem.repository;

import ru.emitrohin.votingsystem.model.DishMenu;

import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface DishMenuRepository extends AbstractRepository<DishMenu> {
    List<DishMenu> findAllByMenuId(int menuId);
}
