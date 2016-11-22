package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.votingsystem.model.MenuDish;
import ru.emitrohin.votingsystem.repository.datajpa.CrudMenuDishRepository;
import ru.emitrohin.votingsystem.repository.interfaces.AbstractRepository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
public class MenuDishRepositoryImpl implements AbstractRepository<MenuDish> {

    @Autowired
    private CrudMenuDishRepository crudRepository;

    @Override
    public MenuDish save(MenuDish menuDish) {
        return crudRepository.save(menuDish);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public MenuDish get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public List<MenuDish> getAll() {
        return crudRepository.findAll();
    }
}
