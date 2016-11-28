package ru.emitrohin.votingsystem.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author emitrohin
 * @version 1.0
 *          25.11.2016
 */

@RestController
@RequestMapping(VoteController.CONTROLLER_URL)
public class VoteController {

    static final String CONTROLLER_URL = RootController.REST_URL + "vote/";

}
