package ru.emitrohin.votingsystem.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.emitrohin.votingsystem.AuthorizedUser;
import ru.emitrohin.votingsystem.model.Vote;
import ru.emitrohin.votingsystem.service.interfaces.VoteService;
import ru.emitrohin.votingsystem.to.VoteTo;
import ru.emitrohin.votingsystem.util.TimeUtil;

import java.net.URI;

import static ru.emitrohin.votingsystem.util.TimeUtil.VOTING_TIME;

/**
 * @author emitrohin
 * @version 1.0
 *          25.11.2016
 */

@RestController
@RequestMapping(VoteController.CONTROLLER_URL)
public class VoteController {

    static final String CONTROLLER_URL = RootController.REST_URL + "votes/";

    private VoteService service;

    @Autowired
    public VoteController(VoteService service) {
        this.service = service;
    }

    @PostMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> vote(@PathVariable("id") int restaurantId) throws Exception {
        Vote newVote = service.vote(AuthorizedUser.id(), restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTROLLER_URL + "/{id}")
                .buildAndExpand(newVote.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(newVote);

    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VoteTo> results() {
        if (TimeUtil.nowTime().compareTo(VOTING_TIME) > 0) {
            VoteTo voteTo = VoteTo.parseResults(service.getAllCurrent());
            return ResponseEntity.ok().body(voteTo);
        }

        VoteTo voteTo = VoteTo.parseSubTotals(service.getAllCurrent());
        return ResponseEntity.ok().body(voteTo);
    }
}
