package com.sda.study.springbootpractice.services;

import com.sda.study.springbootpractice.exceptions.SchoolNotFoundException;
import com.sda.study.springbootpractice.models.School;

import java.util.List;

/**
 * To handle all School related operations
 * CRUD- Create, Read, Update, Delete
 */
public interface SchoolService {

    // CREATE METHOD
    /**
     * To create a new school
     *
     * @param school School
     */
    void createSchool(School school);

    // READ METHODS
    /**
     * To find school by ID
     *
     * @param id School ID
     * @return School
     */
    School findSchoolById(Long id) throws SchoolNotFoundException;

    /**
     * To find school by name
     *
     * @param name School name
     * @return School
     */
    School findSchoolByName(String name) throws SchoolNotFoundException;

    /**
     * To find all schools
     *
     * @return list of schools
     */
    List<School> findAllSchools();

    // UPDATE METHOD
    /**
     * To update an existing School data
     * @param school School
     */
    void updateSchool(School school) throws SchoolNotFoundException;

    // DELETE METHOD

    /**
     * To delete existing School by ID
     * @param id School ID
     */
    void deleteSchoolById(Long id) throws SchoolNotFoundException;

    // RESTORE METHOD

    /**
     * To restore school by ID
     * @param id School ID
     */
    void restoreSchoolById(Long id) throws SchoolNotFoundException;
}
