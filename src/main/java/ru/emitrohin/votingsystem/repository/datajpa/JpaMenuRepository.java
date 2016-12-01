package ru.emitrohin.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.Menu;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
@Transactional(readOnly = true)
public interface JpaMenuRepository extends Repository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Menu u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    Menu save(Menu menu);

    Menu findOne(Integer id);

    List<Menu> findAll();

    List<Menu> findAllByDateOfMenu(LocalDate now);

    Menu findOneByRestaurantIdAndDateOfMenu(int restaurantId, LocalDate now);
}
