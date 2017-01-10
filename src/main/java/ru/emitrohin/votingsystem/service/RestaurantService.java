package ru.emitrohin.votingsystem.service;

import ru.emitrohin.votingsystem.model.Restaurant;

import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   25.11.2016.
 */
public interface RestaurantService extends AbstractService<Restaurant> {
    List<Restaurant> findAllWithCurrentMenu();

    Restaurant findWithCurrentMenu(int restaurantId);
}