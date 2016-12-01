package ru.emitrohin.votingsystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.emitrohin.votingsystem.model.DishMenu;
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
@RequestMapping(DishMenuController.CONTROLLER_URL)
@Secured("ROLE_ADMIN")
public class DishMenuController {

    static final String CONTROLLER_URL = RootController.REST_URL + "dishmenu/";

    private DishMenuService dishMenuService;

    @Autowired
    public DishMenuController(MenuService service, DishMenuService dishMenuService) {
        this.dishMenuService = dishMenuService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DishMenu get(@PathVariable("id") int id) {
        return dishMenuService.get(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishMenu> getAll() {
        return dishMenuService.getAll();
    }

    @GetMapping(value = "/menu/{menuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DishMenu> getAllByMenuId(@PathVariable("menuid") int menuid) {
        return dishMenuService.findAllByMenuId(menuid);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DishMenu> addDishToMenu(@Valid @RequestBody DishMenuTo dishMenuTo) throws Exception {

        DishMenu created = dishMenuService.save(dishMenuTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{price}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateDishInMenu(@Valid @RequestBody DishMenu dishMenu, @PathVariable("price") Double price) {
        dishMenu.setPrice(price);
        DishMenu created = dishMenuService.save(dishMenu);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDishFromMenu(@PathVariable("id") int id) {
        dishMenuService.delete(id);
    }

}
