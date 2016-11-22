package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.repository.datajpa.CrudMenuRepository;
import ru.emitrohin.votingsystem.repository.interfaces.AbstractRepository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
public class MenuRepositoryImpl implements AbstractRepository<Menu> {

    @Autowired
    private CrudMenuRepository crudRepository;

    @Override
    public Menu save(Menu menu) {
        return crudRepository.save(menu);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Menu get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public List<Menu> getAll() {
        return crudRepository.findAll();
    }
}
