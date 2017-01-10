package ru.emitrohin.votingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.emitrohin.votingsystem.AuthorizedUser;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.service.DishService;
import ru.emitrohin.votingsystem.service.MenuService;
import ru.emitrohin.votingsystem.service.RestaurantService;

import java.net.URI;
import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          25.11.2016
 */

@RestController
@RequestMapping(RestaurantController.CONTROLLER_URL)
public class RestaurantController {

    public static final String CONTROLLER_URL = RootController.REST_URL + "restaurants/";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private RestaurantService restaurantService;
    private MenuService menuService;
    private DishService dishService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService, MenuService menuService, DishService dishService) {
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.dishService = dishService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getRestaurantsWithCurrentMenu() {
        log.info(AuthorizedUser.get().getUsername() + " : " + "get current restaurants");
        return restaurantService.findAllWithCurrentMenu();
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteRestaurant(@PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "delete " + id);
        restaurantService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public void updateRestaurant(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "update " + id);
        restaurant.setId(id);
        restaurantService.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody Restaurant restaurant) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "create ");
        Restaurant created = restaurantService.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @GetMapping(value = "/{id}/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu getMenu(@PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + " get menu for restaurant" + id);
        Restaurant restaurant = restaurantService.get(id);
        return menuService.get(restaurant.getMenus().get(0).getId());
    }

    @DeleteMapping(value = "/{id}/menu")
    @Secured("ROLE_ADMIN")
    public void deleteMenu(@PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + " delete menu for restaurant" + id);
        Restaurant restaurant = restaurantService.get(id);
        menuService.delete(restaurant.getMenus().get(0).getId());
    }

    @PostMapping(value = "/{id}/menu", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Menu> createMenu(@PathVariable("id") int restaurantId) {
        log.info(AuthorizedUser.get().getUsername() + " : " + " create menu for restaurant" + restaurantId);
        Menu created = menuService.save(restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}/menu")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
