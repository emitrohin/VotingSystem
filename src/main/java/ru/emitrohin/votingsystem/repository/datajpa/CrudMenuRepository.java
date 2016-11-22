package ru.emitrohin.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.Menu;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
@Transactional(readOnly = true)
public interface CrudMenuRepository extends JpaRepository<Menu, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Menus u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Menu save(Menu menu);

    @Override
    Menu findOne(Integer id);

    @Override
    List<Menu> findAll();

}
