package ru.emitrohin.votingsystem.web;

import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.emitrohin.votingsystem.View;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.service.interfaces.UserService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * @author emitrohin
 * @version 1.0
 *          25.11.2016
 */

@RestController
@RequestMapping(UserController.CONTROLLER_URL)
@Secured("ROLE_ADMIN")
public class UserController {

    static final String CONTROLLER_URL = RootController.REST_URL + "users/";

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> users() {
        return service.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(View.REST.class)
    public User get(@PathVariable("id") int id) {
        return service.get(id);
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody User user, @PathVariable("id") int id) throws BindException {
        user.setId(id);
        service.update(user);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        User created = service.save(user);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PostMapping(value = "/enable/{id}")
    public void enabled(@PathVariable("id") int id, @RequestParam("enabled") boolean enabled) {
        service.enable(id, enabled);
    }

}
