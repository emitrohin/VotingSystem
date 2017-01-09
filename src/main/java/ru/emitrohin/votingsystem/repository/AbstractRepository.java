package ru.emitrohin.votingsystem.repository;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public interface AbstractRepository<T> {

    default T save(T user) {
        throw new UnsupportedOperationException();
    }

    // false if not found
    boolean delete(int id);

    // null if not found
    T get(int id);

    List<T> getAll();
}
