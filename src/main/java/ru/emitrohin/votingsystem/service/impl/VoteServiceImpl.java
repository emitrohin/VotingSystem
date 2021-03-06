package ru.emitrohin.votingsystem.service.impl;

import org.springframework.stereotype.Service;
import ru.emitrohin.votingsystem.model.Menu;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.model.Vote;
import ru.emitrohin.votingsystem.repository.MenuRepository;
import ru.emitrohin.votingsystem.repository.RestaurantRepository;
import ru.emitrohin.votingsystem.repository.UserRepository;
import ru.emitrohin.votingsystem.repository.VoteRepository;
import ru.emitrohin.votingsystem.service.VoteService;
import ru.emitrohin.votingsystem.util.TimeUtil;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;
import ru.emitrohin.votingsystem.util.exception.VotingException;

import java.util.List;

import static ru.emitrohin.votingsystem.util.TimeUtil.VOTING_TIME;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository repository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private MenuRepository menuRepository;

    public VoteServiceImpl(VoteRepository repository, RestaurantRepository restaurantRepository, UserRepository userRepository, MenuRepository menuRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public Vote vote(int userId, int restaurantId) {

        Restaurant restaurant = restaurantRepository.findOne(restaurantId);
        ExceptionUtil.checkNotFoundWithId(restaurant, restaurantId);

        Menu menu = menuRepository.getByRestaurantId(restaurantId);
        if (menuRepository.getByRestaurantId(restaurantId) == null) {
            throw new VotingException("You can't vote for not existing menu");
        }

        if (menu.getDishList().size() == 0) {
            throw new VotingException("Menu for this restaurant is not ready");
        }

        Vote vote = repository.getByUserId(userId, TimeUtil.now());

        if (vote == null) {
            vote = new Vote(null, restaurant, userRepository.findOne(userId), TimeUtil.now());
            return repository.save(vote);
        }

        if (TimeUtil.nowTime().compareTo(VOTING_TIME) > 0) {
            throw new VotingException("You can't change your vote after 11:00");
        }

        vote.setRestaurant(restaurant);
        return repository.save(vote);
    }

    @Override
    public List<Vote> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Vote> getAllCurrent() {
        return repository.findAllCurrent(TimeUtil.now());
    }
}

