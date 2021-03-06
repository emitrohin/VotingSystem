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
import ru.emitrohin.votingsystem.model.Dish;
import ru.emitrohin.votingsystem.service.DishService;

import java.net.URI;

/**
 * @author emitrohin
 * @version 1.0
 *          25.11.2016
 */

@RestController
@RequestMapping(DishController.CONTROLLER_URL)
@Secured("ROLE_ADMIN")
public class DishController {

    public static final String CONTROLLER_URL = RootController.REST_URL + "dishes/";

    private final Logger log = LoggerFactory.getLogger(getClass());

    private DishService service;

    @Autowired
    public DishController(DishService service) {
        this.service = service;
    }


    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "delete " + id);
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Dish dish, @PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "update " + id);
        dish.setId(id);
        service.update(dish);
    }

    @PostMapping(value = "/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish, @PathVariable("menuId") int menuId) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "create");
        Dish created = service.save(dish, menuId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
