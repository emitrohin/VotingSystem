package ru.emitrohin.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.repository.interfaces.RestaurantRepository;
import ru.emitrohin.votingsystem.service.interfaces.RestaurantService;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository repository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository repository) {
        this.repository = repository;
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public Restaurant save(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Restaurant get(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

    @Cacheable("restaurants")
    @Override
    public List<Restaurant> getRestaurantsWithCurrentMenu() {
        return repository.getRestaurantsWithCurrentMenu();
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    @Override
    public void evictCache() {

    }
}
