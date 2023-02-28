package com.sda.study.springbootpractice.services;


import com.sda.study.springbootpractice.exceptions.CourseNotFoundException;
import com.sda.study.springbootpractice.models.Course;
import com.sda.study.springbootpractice.models.School;

import java.util.List;
/**
 * To handle all Course related operations
 * CRUD- Create, Read, Update, Delete
 */
public interface CourseService {
    /**
     * To create a new course
     *
     * @param course Course
     */
    void createCourse(Course course);

    /**
     * To find a course by ID
     *
     * @param id Course ID
     * @return Course
     */
    Course findCourseById(Long id) throws CourseNotFoundException;

    /**
     * To find a course by name
     *
     * @param name Course name
     * @return Course
     */
    Course findCourseByName(String name) throws CourseNotFoundException;

    /**
     * To find all courses
     *
     * @return a list of Course
     */
    List<Course> findAllCourses();

    /**
     * To find all courses by school
     *
     * @param school School
     * @return a list of Course
     */
    List<Course> findAllCoursesBySchool(School school);

    /**
     * To update an existing Course
     *
     * @param course Course
     */
    void updateCourse(Course course) throws CourseNotFoundException;

    /**
     * To delete a Course by ID
     *
     * @param id Course ID
     */
    void deleteCourseById(Long id) throws CourseNotFoundException;

    /**
     * To restore a Course by ID
     *
     * @param id Course ID
     */
    void restoreCourseById(Long id) throws CourseNotFoundException;
}


