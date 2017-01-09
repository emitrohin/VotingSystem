package ru.emitrohin.votingsystem.repository;

import ru.emitrohin.votingsystem.model.User;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface UserRepository extends AbstractRepository<User> {
    User findByLogin(String login);
}
