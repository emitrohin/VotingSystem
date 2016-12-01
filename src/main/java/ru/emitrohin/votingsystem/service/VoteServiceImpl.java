package ru.emitrohin.votingsystem.service;

import org.springframework.stereotype.Service;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.model.Vote;
import ru.emitrohin.votingsystem.repository.interfaces.MenuRepository;
import ru.emitrohin.votingsystem.repository.interfaces.RestaurantRepository;
import ru.emitrohin.votingsystem.repository.interfaces.UserRepository;
import ru.emitrohin.votingsystem.repository.interfaces.VoteRepository;
import ru.emitrohin.votingsystem.service.interfaces.VoteService;
import ru.emitrohin.votingsystem.util.TimeUtil;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;

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
    public Vote vote(int userId, int restaurantId) throws Exception {

        if (TimeUtil.nowTime().compareTo(VOTING_TIME) > 0) {
            throw new Exception("You can't vote for not existing menu");
        }

        Restaurant restaurant = restaurantRepository.get(restaurantId);
        ExceptionUtil.checkNotFoundWithId(restaurant, restaurantId);

        User user = userRepository.get(userId);
        ExceptionUtil.checkNotFoundWithId(user, userId);

        if (menuRepository.getByRestaurantId(restaurantId) == null) {
            throw new Exception("You can't vote for not existing menu");
        }

        Vote vote = repository.getByUserId(userId);
        if (vote == null) {
            vote = new Vote(null, restaurant, user, TimeUtil.now());
            return repository.save(vote);
        }

        vote.setRestaurant(restaurant);
        return repository.save(vote);
    }

    @Override
    public List<Vote> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Vote> getAllCurrent() {
        return repository.getAllCurrent(TimeUtil.now());
    }
}

