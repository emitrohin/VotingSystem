package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.repository.datajpa.JpaRestaurantRepository;
import ru.emitrohin.votingsystem.repository.interfaces.AbstractRepository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Repository
public class RestaurantRepositoryImpl implements AbstractRepository<Restaurant> {

    private JpaRestaurantRepository repository;

    @Autowired
    public RestaurantRepositoryImpl(JpaRestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        if (!restaurant.isNew() && get(restaurant.getId()) == null) {
            return null;
        }
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
}
