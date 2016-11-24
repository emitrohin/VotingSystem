package ru.emitrohin.votingsystem.service.interfaces;

import ru.emitrohin.votingsystem.util.exception.NotFoundException;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public interface AbstractService<T> {

    T save(T item);

    void delete(int id) throws NotFoundException;

    T get(int id) throws NotFoundException;

    List<T> getAll();

    void update(T item);

    void evictCache();


}
