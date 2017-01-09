package ru.emitrohin.votingsystem.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.repository.MenuRepository;
import ru.emitrohin.votingsystem.repository.RestaurantRepository;
import ru.emitrohin.votingsystem.service.MenuService;
import ru.emitrohin.votingsystem.util.TimeUtil;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;
    private RestaurantRepository restaurantRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository repository, RestaurantRepository restaurantRepository) {
        this.menuRepository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Menu save(int restaurantId) {
        Menu menu = new Menu(null, TimeUtil.now());
        menu.setRestaurant(restaurantRepository.findOne(restaurantId));
        return menuRepository.save(menu);
    }

    @Override
    public void delete(int id) {
        menuRepository.delete(id);
    }

    @Override
    public Menu get(int id) {
        return ExceptionUtil.checkNotFoundWithId(menuRepository.findOne(id), id);
    }

    @Override
    public Menu getByRestaurantId(int restaurantId) {
        return ExceptionUtil.checkNotFoundWithId(menuRepository.getByRestaurantId(restaurantId), restaurantId);
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    @Override
    public List<Menu> getAllCurrent() {
        List<Menu> all = menuRepository.findAllByDateOfMenu(TimeUtil.now());
        return all.stream().filter(x -> x.getDishList().size() > 0).collect(Collectors.toList());
    }
}
