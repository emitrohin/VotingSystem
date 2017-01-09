package ru.emitrohin.votingsystem.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.repository.DishRepository;
import ru.emitrohin.votingsystem.repository.datajpa.JpaDishRepository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Repository
public class DishRepositoryImpl implements DishRepository {

    private JpaDishRepository repository;

    @Autowired
    public DishRepositoryImpl(JpaDishRepository repository) {
        this.repository = repository;
    }

    @Override
    public Dish save(Dish dish) {
        return repository.save(dish);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public Dish get(int id) {
        return repository.findOne(id);
    }

    @Override
    public List<Dish> getAll() {
        return repository.findAll();
    }
}
