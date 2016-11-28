package ru.emitrohin.votingsystem.service.interfaces;

import ru.emitrohin.votingsystem.model.DishMenu;
import ru.emitrohin.votingsystem.to.DishMenuTo;

import java.util.List;


/**
 * Author: E_Mitrohin
 * Date:   25.11.2016.
 */
public interface DishMenuService extends AbstractService<DishMenu> {

    DishMenu save(DishMenuTo dishMenuTo) throws Exception;

    void update(DishMenuTo dishMenuTo);

    List<DishMenu> findAllByMenuId(int menuId);

}
