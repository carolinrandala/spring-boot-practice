package com.sda.study.springbootpractice.services;

import com.sda.study.springbootpractice.exceptions.TeacherNotFoundException;
import com.sda.study.springbootpractice.models.School;
import com.sda.study.springbootpractice.models.Teacher;

import java.util.List;
/**
 * To handle all Teacher related operations
 *
 * @author Vinod John
 * @Date 27.02.2023
 */
public interface TeacherService {
    /**
     * To create a new teacher
     *
     * @param teacher Teacher
     */
    void createTeacher(Teacher teacher);

    /**
     * To find a teacher by ID
     *
     * @param id Teacher ID
     * @return Teacher
     */
    Teacher findTeacherById(Long id) throws TeacherNotFoundException;

    /**
     * To find a teacher by name
     *
     * @param name Teacher name
     * @return Teacher
     */
    Teacher findTeacherByName(String name) throws TeacherNotFoundException;

    /**
     * To find all teachers
     *
     * @return a list of Teacher
     */
    List<Teacher> findAllTeachers();

    /**
     * To update an existing Teacher
     *
     * @param teacher Teacher
     */
    void updateTeacher(Teacher teacher) throws TeacherNotFoundException;

    /**
     * To delete a Teacher by ID
     *
     * @param id Teacher ID
     */
    void deleteTeacherById(Long id) throws TeacherNotFoundException;

    /**
     * To restore a Teacher by ID
     *
     * @param id Teacher ID
     */
    void restoreTeacherById(Long id) throws TeacherNotFoundException;
}

