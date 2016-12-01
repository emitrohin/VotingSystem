package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.repository.datajpa.JpaRestaurantRepository;
import ru.emitrohin.votingsystem.repository.interfaces.RestaurantRepository;
import ru.emitrohin.votingsystem.util.TimeUtil;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private JpaRestaurantRepository repository;

    @Autowired
    public RestaurantRepositoryImpl(JpaRestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        return repository.save(restaurant);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return repository.findOne(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Restaurant> getRestaurantsWithCurrentMenu() {
        return repository.getAllWithMenuByDate(TimeUtil.now());
    }
}
