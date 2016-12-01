package ru.emitrohin.votingsystem.util;

import ru.emitrohin.votingsystem.model.User;

public class UserUtil {

    public static User prepareToSave(User user) {
        user.setPassword(PasswordUtil.encode(user.getPassword()));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }
}
