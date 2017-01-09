package ru.emitrohin.votingsystem.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.emitrohin.votingsystem.model.DishMenu;
import ru.emitrohin.votingsystem.repository.DishMenuRepository;
import ru.emitrohin.votingsystem.repository.datajpa.JpaDishMenuRepository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Repository
public class DishMenuRepositoryImpl implements DishMenuRepository {

    private JpaDishMenuRepository repository;

    @Autowired
    public DishMenuRepositoryImpl(JpaDishMenuRepository repository) {
        this.repository = repository;
    }

    @Override
    public DishMenu save(DishMenu dishMenu) {
        return repository.save(dishMenu);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public DishMenu get(int id) {
        return repository.findOne(id);
    }

    @Override
    public List<DishMenu> getAll() {
        return repository.findAll();
    }

    @Override
    public List<DishMenu> findAllByMenuId(int menuId) {
        return repository.findAllByMenuId(menuId);
    }
}
