package ru.emitrohin.votingsystem.web;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User: gkislin
 * Date: 22.08.2014
 */

@RestController
@RequestMapping(RootController.REST_URL)
public class RootController {

    static final String REST_URL = "/api/v1.0/";

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> root() {
        return ResponseEntity.ok().body("This is your success!");
    }

}
