package ru.emitrohin.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.Category;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public interface CrudCategoryRepository extends JpaRepository<Category, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Categories u WHERE u.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Category save(Category category);

    @Override
    Category findOne(Integer id);

    @Override
    List<Category> findAll();

}
