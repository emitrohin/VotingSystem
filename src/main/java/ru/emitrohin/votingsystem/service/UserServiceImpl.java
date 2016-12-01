package ru.emitrohin.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.emitrohin.votingsystem.AuthorizedUser;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.repository.interfaces.UserRepository;
import ru.emitrohin.votingsystem.service.interfaces.UserService;
import ru.emitrohin.votingsystem.util.exception.ExceptionUtil;

import java.util.List;

import static ru.emitrohin.votingsystem.util.UserUtil.prepareToSave;

/**
 * Author: E_Mitrohin
 * Date:   18.11.2016.
 */

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @CacheEvict(value = "users", allEntries = true)
    public User save(User user) {
        Assert.notNull(user, "user must not be null");
        return repository.save(prepareToSave(user));
    }

    @Override
    public void delete(int id) {
        ExceptionUtil.checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public User get(int id) {
        return ExceptionUtil.checkNotFoundWithId(repository.get(id), id);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(prepareToSave(user));
    }

    @Cacheable("users")
    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        repository.save(user);
    }

    @CacheEvict(value = "users", allEntries = true)
    @Override
    public void evictCache() {
    }

    @Override
    public AuthorizedUser loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = repository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User is not found");
        }
        return new AuthorizedUser(user);
    }
}
