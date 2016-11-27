package ru.emitrohin.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.Dish;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
@Transactional(readOnly = true)
public interface JpaDishRepository extends Repository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    Dish save(Dish restaurant);

    Dish findOne(Integer id);

    List<Dish> findAll();
}
