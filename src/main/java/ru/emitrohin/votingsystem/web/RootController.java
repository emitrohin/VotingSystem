package ru.emitrohin.votingsystem.web;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.emitrohin.votingsystem.AuthorizedUser;

/**
 * User: gkislin
 * Date: 22.08.2014
 */

@RestController
@RequestMapping(RootController.REST_URL)
public class RootController {

    public static final String REST_URL = "/api/v1.0/";
    private final Logger log = LoggerFactory.getLogger(getClass());

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> root() {
        log.info(AuthorizedUser.get().getUsername() + " : " + "root");
        return ResponseEntity.ok().body("Welcome to restaurant voting system, here is some curl sample commands to test this service\n" +
                "");
    }

}
