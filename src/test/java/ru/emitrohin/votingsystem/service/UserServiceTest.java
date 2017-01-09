package ru.emitrohin.votingsystem.service;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.service.interfaces.UserService;
import ru.emitrohin.votingsystem.util.exception.NotFoundException;

import java.util.*;

import static ru.emitrohin.votingsystem.model.Role.ROLE_USER;
import static ru.emitrohin.votingsystem.testdata.UserTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected UserService service;

    @Test
    public void testSave() throws Exception {
        User newUser = new User(null, "New", "Password", "new@email.not", "Ololoev", "ololosha", true, EnumSet.of(ROLE_USER));
        User created = service.save(newUser);
        newUser.setId(created.getId());
        Collection<User> result = new ArrayList<>(TEST_USERS);
        result.add(created);
        MATCHER.assertCollectionEquals(result, service.getAll());
    }

    @Test(expected = DataAccessException.class)
    public void testDuplicateLoginSave() throws Exception {
        service.save(new User(null, "A_Ustumov", "Password", "new@email.not", "Ololoev", "ustimov", true, EnumSet.of(ROLE_USER)));
    }


    @Test
    public void testDelete() throws Exception {
        service.delete(100005);
        List<User> result = new ArrayList<>(TEST_USERS);
        result.remove(result.get(5));
        MATCHER.assertCollectionEquals(Collections.unmodifiableList(result), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(100000);
        MATCHER.assertEquals(TEST_USERS.get(0), user);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<User> all = service.getAll();
        MATCHER.assertCollectionEquals(TEST_USERS, all);
    }


    @Test
    public void testUpdate() throws Exception {
        User updated = new User(TEST_USERS.get(0));
        updated.setFirstName("UpdatedName");
        updated.setLastName("UpdatedLAstName");
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(100000));
    }

    @Test
    public void testSetEnabledEquals() {
        service.enable(100000, false);
        Assert.assertFalse(service.get(100000).isEnabled());
        service.enable(100000, true);
        Assert.assertTrue(service.get(100000).isEnabled());
    }
}