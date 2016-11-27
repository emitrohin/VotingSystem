package ru.emitrohin.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.repository.interfaces.AbstractRepository;
import ru.emitrohin.votingsystem.service.interfaces.RestaurantService;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service
public class RestaurantServiceImpl implements RestaurantService {

    private AbstractRepository<Restaurant> repository;

    @Autowired
    public RestaurantServiceImpl(AbstractRepository<Restaurant> repository) {
        this.repository = repository;
    }

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

    @Override
    public void update(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        repository.save(restaurant);
    }

    @Override
    public List<Restaurant> getAll() {
        return repository.getAll();
    }

}
