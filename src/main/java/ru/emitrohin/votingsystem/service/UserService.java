package ru.emitrohin.votingsystem.service;

import ru.emitrohin.votingsystem.model.User;

/**
 * @author emitrohin
 * @version 1.0
 *          24.11.2016
 */
public interface UserService extends AbstractService<User>{
    void enable(int id, boolean enabled);
}
