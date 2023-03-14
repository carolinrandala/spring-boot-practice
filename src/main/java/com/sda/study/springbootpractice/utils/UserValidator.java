package com.sda.study.springbootpractice.utils;

import com.sda.study.springbootpractice.exceptions.UserValidationException;
import com.sda.study.springbootpractice.models.User;
import com.sda.study.springbootpractice.models.UserType;

/**
 * Validation util for User
 */
public class UserValidator {
    public boolean isAdminUser(User user) throws UserValidationException {
        String fullName = user.getFirstName() + " " + user.getLastName();

        if (user.getUserType() == null) {
            throw new NullPointerException(String.format("The user type is null for user: %s", fullName));
        }
        if (UserType.ADMIN.equals(user.getUserType())) {
            return true;
        } else {
            throw new UserValidationException(fullName, "User admin check failed!");
        }
    }
}
