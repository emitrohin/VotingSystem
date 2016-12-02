package ru.emitrohin.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.repository.interfaces.DishRepository;
import ru.emitrohin.votingsystem.service.interfaces.DishService;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service
public class DishServiceImpl implements DishService {

    private DishRepository repository;

    @Autowired
    public DishServiceImpl(DishRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public Dish save(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        return repository.save(dish);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Dish get(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public void update(Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        repository.save(dish);
    }

    @Cacheable("dishes")
    @Override
    public List<Dish> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "dishes", allEntries = true)
    @Override
    public void evictCache() {

    }
}
