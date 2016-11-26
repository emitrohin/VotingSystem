package ru.emitrohin.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.repository.datajpa.JpaUserRepository;
import ru.emitrohin.votingsystem.repository.interfaces.AbstractRepository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Repository
public class UserRepositoryImpl implements AbstractRepository<User> {

    @Autowired
    private JpaUserRepository repository;

    @Override
    public User save(User user) {
        if (!user.isNew() && get(user.getId()) == null) {
            return null;
        }
        return repository.save(user);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id) != 0;
    }

    @Override
    public User get(int id) {
        return repository.findOne(id);
    }

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }
}
