package com.sda.study.springbootpractice.models;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

/**
 * User model
 */
@Data
public class User {
    private String firstName;
    private String lastName;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserType userType;

}
