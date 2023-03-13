package com.sda.study.springbootpractice.exceptions;

/**
 * Exception to handle authority not found
 */
public class AuthorityNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public AuthorityNotFoundException(String name) {
        super(String.format("Authority not found for name: %s!", name));
    }
}
