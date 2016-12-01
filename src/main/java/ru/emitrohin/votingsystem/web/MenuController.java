package ru.emitrohin.votingsystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.emitrohin.votingsystem.model.DishMenu;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.service.interfaces.DishMenuService;
import ru.emitrohin.votingsystem.service.interfaces.MenuService;
import ru.emitrohin.votingsystem.to.DishMenuTo;

import javax.validation.Valid;
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

    static final String CONTROLLER_URL = RootController.REST_URL + "menus/";

    private MenuService service;

    private DishMenuService dishMenuService;

    @Autowired
    public MenuController(MenuService service, DishMenuService dishMenuService) {
        this.service = service;
        this.dishMenuService = dishMenuService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> menus() {
        return service.getAllCurrent();
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Menu> menusAll() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @Secured("ROLE_ADMIN")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PostMapping(value = "restaurant/{restaurantId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<Menu> create(@PathVariable("restaurantId") Integer restaurantId) {

        Menu created = service.save(restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    /* dish menus */

    @PostMapping(value = "dishmenu/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public ResponseEntity<DishMenu> addDishToMenu(@Valid @RequestBody DishMenuTo dishMenuTo) throws Exception {

        DishMenu created = dishMenuService.save(dishMenuTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "dishmenu/{price}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_ADMIN")
    public void updateDishInMenu(@Valid @RequestBody DishMenu dishMenu, @PathVariable("price") Double price) {
        dishMenu.setPrice(price);
        DishMenu created = dishMenuService.save(dishMenu);
    }

    @DeleteMapping(value = "dishmenu/{id}")
    @Secured("ROLE_ADMIN")
    public void deleteDishFromMenu(@PathVariable("id") int id) {
        dishMenuService.delete(id);
    }

}
