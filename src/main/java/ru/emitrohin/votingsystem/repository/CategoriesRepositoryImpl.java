package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.votingsystem.model.Category;
import ru.emitrohin.votingsystem.repository.datajpa.CrudCategoryRepository;
import ru.emitrohin.votingsystem.repository.interfaces.AbstractRepository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */
public class CategoriesRepositoryImpl implements AbstractRepository<Category> {

    @Autowired
    private CrudCategoryRepository crudRepository;

    @Override
    public Category save(Category category) {
        return crudRepository.save(category);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Category get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public List<Category> getAll() {
        return crudRepository.findAll();
    }
}
