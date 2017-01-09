package ru.emitrohin.votingsystem.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.emitrohin.votingsystem.AuthorizedUser;
import ru.emitrohin.votingsystem.model.Vote;
import ru.emitrohin.votingsystem.service.interfaces.VoteService;
import ru.emitrohin.votingsystem.to.VoteTo;

import java.net.URI;

/**
 * @author emitrohin
 * @version 1.0
 *          25.11.2016
 */

@RestController
@RequestMapping(RootController.REST_URL)
public class VoteController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    @PostMapping(value = "/restaurants/{id}/vote", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@PathVariable("id") int restaurantId) throws Exception {
        log.info(AuthorizedUser.get().getUsername() + " : vote " + restaurantId);
        Vote newVote = service.vote(AuthorizedUser.id(), restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(RootController.REST_URL + "/restaurants/{id}/vote")
                .buildAndExpand(newVote.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newVote);

    }

    @GetMapping(value = "/restaurants/votes", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteTo> results() {
        log.info(AuthorizedUser.get().getUsername() + " : results ");

        VoteTo voteTo = VoteTo.parseResults(service.getAllCurrent());
        return ResponseEntity.ok().body(voteTo);
    }
}
