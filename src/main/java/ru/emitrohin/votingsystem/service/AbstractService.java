package ru.emitrohin.votingsystem.service;

import org.springframework.dao.EmptyResultDataAccessException;
import ru.emitrohin.votingsystem.util.exception.NotFoundException;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public interface AbstractService<T> {

    default T save(T item) {
        throw new UnsupportedOperationException();
    }

    void delete(int id) throws EmptyResultDataAccessException;

    T get(int id) throws NotFoundException;

    List<T> getAll();

    default void update(T item) {
        throw new UnsupportedOperationException();
    }
}
