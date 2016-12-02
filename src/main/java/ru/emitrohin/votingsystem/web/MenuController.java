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
import ru.emitrohin.votingsystem.service.interfaces.MenuService;

import java.net.URI;
import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          25.11.2016
 */

@RestController
@RequestMapping(MenuController.CONTROLLER_URL)
public class MenuController {

    public static final String CONTROLLER_URL = RootController.REST_URL + "menus/";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private MenuService service;

    @Autowired
    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> menus() {
        log.info(AuthorizedUser.get().getUsername() + " : " + "menus");
        return service.getAllCurrent();
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public List<Menu> menusAll() {
        log.info(AuthorizedUser.get().getUsername() + " : " + "menus all");
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "get " + id);
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "delete " + id);
        service.delete(id);
    }

    @PostMapping(value = "restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Menu> create(@PathVariable("restaurantId") Integer restaurantId) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "create " + restaurantId);
        Menu created = service.save(restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
