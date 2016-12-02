package ru.emitrohin.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.emitrohin.votingsystem.model.DishMenu;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.repository.interfaces.DishMenuRepository;
import ru.emitrohin.votingsystem.repository.interfaces.DishRepository;
import ru.emitrohin.votingsystem.repository.interfaces.MenuRepository;
import ru.emitrohin.votingsystem.service.interfaces.DishMenuService;
import ru.emitrohin.votingsystem.to.DishMenuTo;
import ru.emitrohin.votingsystem.util.DishMenuUtil;
import ru.emitrohin.votingsystem.util.TimeUtil;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;
import ru.emitrohin.votingsystem.util.exception.MenuException;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service
public class DishMenuServiceImpl implements DishMenuService {

    private DishMenuRepository dishMenuRepository;
    private DishRepository dishRepository;
    private MenuRepository menuRepository;

    @Autowired
    public DishMenuServiceImpl(DishMenuRepository repository, DishRepository dishRepository, MenuRepository menuRepository) {
        this.dishMenuRepository = repository;
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    @CacheEvict(value = "dishmenus", allEntries = true)
    public DishMenu save(DishMenuTo dishMenuTo) {
        Assert.notNull(dishMenuTo, "dishMenuTo must not be null");

        Menu menu = menuRepository.get(dishMenuTo.getMenuId());
        ExceptionUtil.checkNotFoundWithId(menu, dishMenuTo.getMenuId());
        if (menu.getDateOfMenu().compareTo(TimeUtil.now()) < 0) {
            throw new MenuException("Date of menu if lower than now");
        }
        ExceptionUtil.checkNotFoundWithId(dishRepository.get(dishMenuTo.getDishId()), dishMenuTo.getDishId());

        DishMenu newDishMenu = DishMenuUtil.createNewFromTo(dishMenuTo);
        return dishMenuRepository.save(newDishMenu);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(dishMenuRepository.delete(id), id);
    }

    @Override
    public DishMenu get(int id) {
        return ExceptionUtil.checkNotFoundWithId(dishMenuRepository.get(id), id);
    }

    @Override
    @CacheEvict(value = "dishmenus", allEntries = true)
    public void update(DishMenuTo dishMenuTo) {
        Assert.notNull(dishMenuTo, "dish must not be null");
        DishMenu newDishMenu = DishMenuUtil.createNewFromTo(dishMenuTo);
        dishMenuRepository.save(newDishMenu);
    }

    @Override
    @CacheEvict(value = "dishmenus", allEntries = true)
    public void evictCache() {

    }

    @Cacheable("dishmenus")
    @Override
    public List<DishMenu> getAll() {
        return dishMenuRepository.getAll();
    }

    @Override
    public List<DishMenu> findAllByMenuId(int menuId) {
        return dishMenuRepository.findAllByMenuId(menuId);
    }
}
