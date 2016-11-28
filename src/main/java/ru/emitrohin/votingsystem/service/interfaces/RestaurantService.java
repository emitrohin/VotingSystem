package ru.emitrohin.votingsystem.service.interfaces;

import ru.emitrohin.votingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   25.11.2016.
 */
public interface RestaurantService extends AbstractService<Restaurant> {
    List<Restaurant> getAllWithMenuByDate(LocalDate date);
}