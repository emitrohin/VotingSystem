package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.repository.datajpa.CrudRestaurantRepository;
import ru.emitrohin.votingsystem.repository.interfaces.AbstractRepository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
public class RestaurantRespositoryImpl implements AbstractRepository<Restaurant> {

    @Autowired
    private CrudRestaurantRepository crudRepository;

    @Override
    public Restaurant save(Restaurant user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Restaurant get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public List<Restaurant> getAll() {
        return crudRepository.findAll();
    }
}
