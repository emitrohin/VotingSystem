package ru.emitrohin.votingsystem.repository.interfaces;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public interface AbstractRepository<T> {

    T save(T user);

    // false if not found
    boolean delete(int id);

    // null if not found
    T get(int id);

    List<T> getAll();
}
