package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.repository.datajpa.CrudDishRepository;
import ru.emitrohin.votingsystem.repository.interfaces.AbstractRepository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
public class DishRepositoryImpl implements AbstractRepository<Dish> {

    @Autowired
    private CrudDishRepository crudRepository;

    @Override
    public Dish save(Dish dish) {
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public List<Dish> getAll() {
        return crudRepository.findAll();
    }
}
