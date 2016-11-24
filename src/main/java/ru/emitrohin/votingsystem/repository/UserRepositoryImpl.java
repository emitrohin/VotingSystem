package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.repository.datajpa.CrudUserRepository;
import ru.emitrohin.votingsystem.repository.interfaces.AbstractRepository;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Repository
public class UserRepositoryImpl implements AbstractRepository<User> {

    @Autowired
    private CrudUserRepository crudRepository;

    @Override
    public User save(User user) {
        return crudRepository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return crudRepository.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return crudRepository.findAll();
    }
}
