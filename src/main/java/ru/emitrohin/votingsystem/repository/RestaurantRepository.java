package ru.emitrohin.votingsystem.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface RestaurantRepository extends Repository<Restaurant, Integer> {

    @Transactional
    @Modifying
    void delete(int id);

    @Transactional
    Restaurant save(Restaurant restaurant);

    Restaurant findOne(Integer id);

    List<Restaurant> findAll();

    @Query("SELECT r FROM Restaurant r JOIN r.menus a WHERE a.dateOfMenu = ?1")
    List<Restaurant> getAllWithMenuByDate(LocalDate date);
}
