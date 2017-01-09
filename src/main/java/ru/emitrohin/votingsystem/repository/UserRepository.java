package ru.emitrohin.votingsystem.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.emitrohin.votingsystem.model.User;

import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface UserRepository extends Repository<User, Integer> {

    @Transactional
    @Modifying
    void delete(@Param("id") int id);

    @Transactional
    User save(User user);

    User findOne(Integer id);

    List<User> findAll();

    User findByLogin(String login);

}
