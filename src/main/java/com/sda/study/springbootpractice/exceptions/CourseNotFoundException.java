package com.sda.study.springbootpractice.exceptions;

public class CourseNotFoundException extends Exception{
    private static final long serialVersionUID = 1L;

    public CourseNotFoundException(Long id) {

        super(String.format("School not found for id: %d", id));
    }
    public CourseNotFoundException(String name) {

        super(String.format("School not found for name: %s", name));
    }
}


