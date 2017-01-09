package ru.emitrohin.votingsystem.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.Dish;

import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface DishRepository extends Repository<Dish, Integer> {

    @Transactional
    @Modifying
    void delete(int id);

    @Transactional
    Dish save(Dish dish);

    Dish findOne(Integer id);

    List<Dish> findAll();
}
