package com.sda.study.springbootpractice.services.implementations;

import com.sda.study.springbootpractice.exceptions.UserNotFoundException;
import com.sda.study.springbootpractice.models.User;
import com.sda.study.springbootpractice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Customized implementation of UserDetailsService
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findUserByUsername(username);
            return new CustomUserDetails(user);
        } catch (UserNotFoundException e) {
            throw new UsernameNotFoundException(e.getLocalizedMessage());
        }
    }
}
