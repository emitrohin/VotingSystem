package ru.emitrohin.votingsystem.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.service.interfaces.AbstractService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static ru.emitrohin.votingsystem.testdata.UserTestData.MATCHER;
import static ru.emitrohin.votingsystem.testdata.UserTestData.TEST_USERS;

public class UserServiceTest extends AbstractServiceTest {

    @Autowired
    protected AbstractService<User> service;

    @Before
    public void setUp() throws Exception {
        service.evictCache();
    }

    @Test
    public void testSave() throws Exception {

        User newUser = new User(null, "New", "Password", "new@email.not", "Ololoev", "ololosha", new Date(), null, true);
        User created = service.save(newUser);
        newUser.setId(created.getId());
        Collection<User> result = new ArrayList<>(TEST_USERS);
        result.add(created);
        MATCHER.assertCollectionEquals(result, service.getAll());
    }

   /* @Test(expected = DataAccessException.class)
    public void testDuplicateMailSave() throws Exception {
        service.save(new User(null, "Duplicate", "user@yandex.ru", "newPass", 2000, Role.ROLE_USER));
    }

    @Test
    public void testDelete() throws Exception {
        service.delete(USER_ID);
        MATCHER.assertCollectionEquals(Collections.singletonList(ADMIN), service.getAll());
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        service.delete(1);
    }

    @Test
    public void testGet() throws Exception {
        User user = service.get(ADMIN_ID);
        MATCHER.assertEquals(ADMIN, user);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        service.get(1);
    }

    @Test
    public void testGetByEmail() throws Exception {
        User user = service.getByEmail("admin@gmail.com");
        MATCHER.assertEquals(ADMIN, user);
    }

    @Test
    public void testGetAll() throws Exception {
        Collection<User> all = service.getAll();
        MATCHER.assertCollectionEquals(Arrays.asList(ADMIN, USER), all);
    }

    @Test
    public void testUpdate() throws Exception {
        User updated = new User(USER);
        updated.setName("UpdatedName");
        updated.setCaloriesPerDay(330);
        updated.setRoles(Collections.singletonList(Role.ROLE_ADMIN));
        service.update(updated);
        MATCHER.assertEquals(updated, service.get(USER_ID));
    }*/

   /* @Test
    public void testSetEnabledEquals() {
        service.enable(USER_ID, false);
        Assert.assertFalse(service.get(USER_ID).isEnabled());
        service.enable(USER_ID, true);
        Assert.assertTrue(service.get(USER_ID).isEnabled());
    }*/
}