package ru.emitrohin.votingsystem.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface MenuRepository extends Repository<Menu, Integer> {

    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Transactional
    Menu save(Menu menu);

    Menu findOne(Integer id);

    List<Menu> findAll();

    List<Menu> findAllByDateOfMenu(LocalDate now);

    Menu findOneByRestaurantIdAndDateOfMenu(int restaurantId, LocalDate now);

    Menu getByRestaurantId(int restaurantId);
}
