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
import ru.emitrohin.votingsystem.model.DishMenu;
import ru.emitrohin.votingsystem.service.DishMenuService;
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

    public static final String CONTROLLER_URL = RootController.REST_URL + "dishmenus/";
    private final Logger log = LoggerFactory.getLogger(getClass());
    private DishMenuService dishMenuService;

    @Autowired
    public DishMenuController(DishMenuService dishMenuService) {
        this.dishMenuService = dishMenuService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DishMenu get(@PathVariable("id") int id) {
        log.info(AuthorizedUser.get().getUsername() + " : " + "create");
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
    public ResponseEntity<DishMenu> addDishToMenu(@Valid @RequestBody DishMenuTo dishMenuTo) {

        DishMenu created = dishMenuService.save(dishMenuTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateDishInMenu(@Valid @RequestBody DishMenu dishMenu, @PathVariable("id") int id) {
        dishMenu.setId(id);
        dishMenuService.save(dishMenu);
    }

    @DeleteMapping(value = "/{id}")
    public void deleteDishFromMenu(@PathVariable("id") int id) {
        dishMenuService.delete(id);
    }

}
