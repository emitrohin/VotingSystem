package ru.emitrohin.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.DishMenu;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
@Transactional(readOnly = true)
public interface JpaDishMenuRepository extends Repository<DishMenu, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM DishMenu u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Transactional
    DishMenu save(DishMenu dishMenu);

    DishMenu findOne(Integer id);

    List<DishMenu> findAll();

    List<DishMenu> findAllByMenuId(int menuId);
}
