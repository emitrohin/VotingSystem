package ru.emitrohin.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.repository.interfaces.MenuRepository;
import ru.emitrohin.votingsystem.service.interfaces.MenuService;
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

    private MenuRepository repository;

    @Autowired
    public MenuServiceImpl(MenuRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public Menu save(int restaurantId) {
        return repository.save(new Menu(null, TimeUtil.now()), restaurantId);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Menu get(int id) {

        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Menu getByRestaurantId(int restaurantId) {

        return ExceptionUtil.checkNotFoundWithId(repository.getByRestaurantId(restaurantId), restaurantId);
    }

    @Cacheable("menus")
    @Override
    public List<Menu> getAll() {
        return repository.getAll();
    }

    @Cacheable("menus")
    @Override
    public List<Menu> getAllCurrent() {
        List<Menu> all = repository.getAllCurrent();
        return all.stream().filter(x -> x.getDishMenus().size() > 0).collect(Collectors.toList());
    }

    @CacheEvict(value = "menus", allEntries = true)
    @Override
    public void evictCache() {

    }
}
