package com.sda.study.springbootpractice.services;

import com.sda.study.springbootpractice.exceptions.UserNotFoundException;
import com.sda.study.springbootpractice.models.User;

import java.util.List;

/**
 * Service handle user related operations
 */
public interface UserService {
    /**
     * To find all users
     * @return a list of users
     */
    List<User> findAllUsers();

    /**
     * To find user by Username
     * @param username username
     * @return user
     */
    User findUserByUsername(String username) throws UserNotFoundException;

    /**
     * To create a new user
     * @param user User
     */
    void createUser(User user);

}
