package ru.emitrohin.votingsystem.service;

import org.springframework.stereotype.Service;
import ru.emitrohin.votingsystem.model.Restaurant;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.model.Vote;
import ru.emitrohin.votingsystem.repository.interfaces.RestaurantRepository;
import ru.emitrohin.votingsystem.repository.interfaces.UserRepository;
import ru.emitrohin.votingsystem.repository.interfaces.VoteRepository;
import ru.emitrohin.votingsystem.service.interfaces.VoteService;
import ru.emitrohin.votingsystem.to.VoteTo;
import ru.emitrohin.votingsystem.util.TimeUtil;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;

import java.time.LocalTime;
import java.util.List;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service
public class VoteServiceImpl implements VoteService {

    private VoteRepository repository;
    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;

    public VoteServiceImpl(VoteRepository repository, RestaurantRepository restaurantRepository, UserRepository userRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Vote vote(int userId, int restaurantId) throws Exception {

        if (TimeUtil.nowTime().compareTo(LocalTime.of(11, 0)) > 0) {
            throw new Exception("Voting is over. Votes are accepted before 11:00");
        }

        Restaurant restaurant = restaurantRepository.get(restaurantId);
        ExceptionUtil.checkNotFoundWithId(restaurant, restaurantId);

        User user = userRepository.get(userId);
        ExceptionUtil.checkNotFoundWithId(user, userId);

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

    @Override
    public VoteTo getSubtotals() {
        return null;
    }
}

