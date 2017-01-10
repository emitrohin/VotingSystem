package ru.emitrohin.votingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.repository.DishRepository;
import ru.emitrohin.votingsystem.repository.MenuRepository;
import ru.emitrohin.votingsystem.service.DishService;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service
public class DishServiceImpl implements DishService {

    private DishRepository dishRepository;
    private MenuRepository menuRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository, MenuRepository menuRepository) {
        this.dishRepository = dishRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public Dish save(Dish dish, int menuId) {
        Assert.notNull(dish, "dish must not be null");
        dish.setMenu(menuRepository.findOne(menuId));
        return dishRepository.save(dish);
    }

    @Override
    public void delete(int id) {
        dishRepository.delete(id);
    }

    @Override
    public Dish get(int id) {
        return ExceptionUtil.checkNotFoundWithId(dishRepository.findOne(id), id);
    }

    @Override
    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        dishRepository.save(dish);
    }

    @Override
    public List<Dish> getAll() {
        return dishRepository.findAll();
    }

}
