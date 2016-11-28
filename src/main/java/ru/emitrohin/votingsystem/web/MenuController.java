package ru.emitrohin.votingsystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    //TODO: should return only current menus
    public List<Menu> menus() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Menu get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PostMapping(value = "restaurant/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> create(@Valid @RequestBody Menu menu, @PathVariable("restaurantId") Integer restaurantId) {

        if (restaurantId == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        Menu created = service.save(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    /* dish menus */

    @PostMapping(value = "dishmenu/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishMenu> addDishToMenu(@Valid @RequestBody DishMenuTo dishMenuTo) throws Exception {

        DishMenu created = dishMenuService.save(dishMenuTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "dishmenu/{price}/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateDishInMenu(@Valid @RequestBody DishMenu dishMenu, @PathVariable("price") Double price) {
        dishMenu.setPrice(price);
        DishMenu created = dishMenuService.save(dishMenu);
    }

    @DeleteMapping(value = "dishmenu/{id}")
    public void deleteDishFromMenu(@PathVariable("id") int id) {
        dishMenuService.delete(id);
    }

}
