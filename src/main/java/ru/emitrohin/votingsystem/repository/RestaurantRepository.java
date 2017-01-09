package ru.emitrohin.votingsystem.repository;

import ru.emitrohin.votingsystem.model.Restaurant;

import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          26.11.2016
 */
public interface RestaurantRepository extends AbstractRepository<Restaurant> {
    List<Restaurant> getRestaurantsWithCurrentMenu();
}
