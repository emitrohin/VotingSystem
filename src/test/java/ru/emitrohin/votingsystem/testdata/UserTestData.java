package ru.emitrohin.votingsystem.testdata;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.emitrohin.votingsystem.matcher.ModelMatcher;
import ru.emitrohin.votingsystem.model.User;
import ru.emitrohin.votingsystem.util.PasswordUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Author: E_Mitrohin
 * Date:   22.11.2016.
 */
public class UserTestData {
    public static final ModelMatcher<User> MATCHER = ModelMatcher.of(User.class,
            (expected, actual) -> expected == actual ||
                    (/*comparePassword(expected.getPassword(), actual.getPassword())*/
                            Objects.equals(expected.getId(), actual.getId())
                                    && Objects.equals(expected.getFirstName(), actual.getFirstName())
                                    && Objects.equals(expected.getLastName(), actual.getLastName())
                                    && Objects.equals(expected.getEmail(), actual.getEmail())
                                    && Objects.equals(expected.getLogin(), actual.getLogin())
                                    && Objects.equals(expected.getPassword(), actual.getPassword())
                    )
    );
    private static final Logger LOG = LoggerFactory.getLogger(UserTestData.class);
    public static List<User> TEST_USERS = new ArrayList<>();

    static {
        TEST_USERS.add(new User(100000, "E_Mitrohin", "admin", "emitrohin@icloud.com", "Евгений", "Митрохин", true));
        TEST_USERS.add(new User(100001, "D_Uskov", "uskov", "duskov@nomail.com", "Денис", "Усков", true));
        TEST_USERS.add(new User(100002, "L_Lapteva", "lapteva", "llapteva@nomail.com", "Людмила", "Лаптева", true));
        TEST_USERS.add(new User(100003, "Y_Mitrohina", "mitrohina", "ymitrohina@nomail.com", "Юлия", "Митрохина", true));
        TEST_USERS.add(new User(100004, "N_Gimaldinova", "gimaldinona", "ngimaldinova@nomail.com", "Наталья", "Гимальдинова", true));
        TEST_USERS.add(new User(100005, "A_Ustumov", "ustimov", "austimov@nomail.com", "Александр", "Устимов", true));
        TEST_USERS.add(new User(100006, "N_Dubanich", "dubanich", "ndubanich@nomail.com", "Николай", "Дубанич", true));
        TEST_USERS.add(new User(100007, "O_Domashnikov", "domashnikov", "odomashnikov@nomail.com", "Олег", "Домашников", true));
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
