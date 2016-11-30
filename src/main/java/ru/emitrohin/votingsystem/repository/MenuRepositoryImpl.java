package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.repository.datajpa.JpaMenuRepository;
import ru.emitrohin.votingsystem.repository.datajpa.JpaRestaurantRepository;
import ru.emitrohin.votingsystem.repository.interfaces.MenuRepository;

import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
@Repository
public class MenuRepositoryImpl implements MenuRepository {

    private JpaMenuRepository menuRepository;

    private JpaRestaurantRepository restaurantRepository;

    @Autowired
    public MenuRepositoryImpl(JpaMenuRepository menuRepository, JpaRestaurantRepository restaurantRepository) {
        this.menuRepository = menuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Menu save(Menu menu, int restaurantId) {
        menu.setRestaurant(restaurantRepository.findOne(restaurantId));
        return menuRepository.save(menu);
    }

    @Override
    public boolean delete(int id) {
        return menuRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return menuRepository.findOne(id);
    }

    @Override
    public List<Menu> getAll() {
        return menuRepository.findAll();
    }
}
