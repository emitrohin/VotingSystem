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
import ru.emitrohin.votingsystem.model.Restaurant;
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
    private RestaurantService service;

    @Autowired
    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public List<Restaurant> restaurants() {
        log.info(AuthorizedUser.get().getUsername() + " : " + "getAll");
        return service.getAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getRestaurantsWithCurrentMenu() {
        log.info(AuthorizedUser.get().getUsername() + " : " + "get current restaurants");
        return service.getRestaurantsWithCurrentMenu();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant get(@PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "get " + id);
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "delete " + id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "update " + id);
        restaurant.setId(id);
        service.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "create ");
        Restaurant created = service.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
