package ru.emitrohin.votingsystem.testdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.emitrohin.votingsystem.matcher.ModelMatcher;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.util.PasswordUtil;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Objects;

import static ru.emitrohin.votingsystem.model.Role.ROLE_ADMIN;
import static ru.emitrohin.votingsystem.model.Role.ROLE_USER;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public class UserTestData {

    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);
    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getFirstName(), actual.getFirstName())
                                    && Objects.equals(expected.getLastName(), actual.getLastName())
                                    && Objects.equals(expected.getEmail(), actual.getEmail())
                                    && Objects.equals(expected.getLogin(), actual.getLogin())
                                    && comparePassword(expected.getPassword(), actual.getPassword())
                    )
    );
    public static List<User> TEST_USERS = new ArrayList<>();

    static {
        reinit();
    }

    public static void reinit() {
        TEST_USERS.clear();
        TEST_USERS.add(new User(100000, "admin", "admin", "admin@icloud.com", "Евгений", "Митрохин", true, EnumSet.of(ROLE_USER, ROLE_ADMIN)));
        TEST_USERS.add(new User(100001, "user1", "uskov", "user1@nomail.com", "Денис", "Усков", true, EnumSet.of(ROLE_USER, ROLE_ADMIN)));
        TEST_USERS.add(new User(100002, "user2", "lapteva", "user2@nomail.com", "Людмила", "Лаптева", true, EnumSet.of(ROLE_USER, ROLE_ADMIN)));
        TEST_USERS.add(new User(100003, "user3", "mitrohina", "user3@nomail.com", "Юлия", "Митрохина", true, EnumSet.of(ROLE_USER, ROLE_ADMIN)));
        TEST_USERS.add(new User(100004, "user4", "gimaldinona", "user4@nomail.com", "Наталья", "Гимальдинова", true, EnumSet.of(ROLE_USER, ROLE_ADMIN)));
        TEST_USERS.add(new User(100005, "user5", "ustimov", "user5@nomail.com", "Александр", "Устимов", true, EnumSet.of(ROLE_USER, ROLE_ADMIN)));
        TEST_USERS.add(new User(100006, "user6", "dubanich", "user6@nomail.com", "Николай", "Дубанич", false, EnumSet.of(ROLE_USER, ROLE_ADMIN)));
        TEST_USERS.add(new User(100007, "user7", "domashnikov", "user7@nomail.com", "Олег", "Домашников", true, EnumSet.of(ROLE_USER, ROLE_ADMIN)));
    }

    private static boolean comparePassword(String rawOrEncodedPassword, String password) {
        if (PasswordUtil.isEncoded(rawOrEncodedPassword)) {
            return rawOrEncodedPassword.equals(password);
        } else if (!PasswordUtil.isMatch(rawOrEncodedPassword, password)) {
            LOG.error("Password " + password + " doesn't match encoded " + password);
            return false;
        }
        return true;
    }
}
