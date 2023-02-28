package com.sda.study.springbootpractice.components;

import com.sda.study.springbootpractice.exceptions.CourseNotFoundException;
import com.sda.study.springbootpractice.exceptions.SchoolNotFoundException;
import com.sda.study.springbootpractice.exceptions.StudentNotFoundException;
import com.sda.study.springbootpractice.exceptions.TeacherNotFoundException;
import com.sda.study.springbootpractice.models.*;
import com.sda.study.springbootpractice.services.CourseService;
import com.sda.study.springbootpractice.services.SchoolService;
import com.sda.study.springbootpractice.services.StudentService;
import com.sda.study.springbootpractice.services.TeacherService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;

/**
 * Component to initialize data on application startup
 */
@Component
public class DataInit {
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private CourseService courseService;

    @PostConstruct // this method will be executed automatically when application will start
    public void init() throws CourseNotFoundException, SchoolNotFoundException {
        initSchool();
        initCourse();
        initTeacher();
        initStudent();
    }

    // PRIVATE METHODS //
    private void initSchool() {
        System.out.println("Starting School initialization...");
        School school = new School();
        school.setName("Tartu University");
        school.setAddress("Ülikooli 18, Tartu");
        school.setPhone("+3725879287");

        // to avoid duplicated values in database
        try {
            School searchSchool = schoolService.findSchoolByName(school.getName());
            System.out.println("Cannot pre-initialize school: " + school.getName());
        } catch (SchoolNotFoundException e) {
            schoolService.createSchool(school);
        }
    }

    private void initCourse() {
        System.out.println("Starting course initialization...");

        try {
            School searchSchool = schoolService.findSchoolByName("Tartu University");

            Course course = new Course();
            course.setName("Mathematics");
            course.setSchool(searchSchool);
            course.setStartDate(LocalDate.now());
            course.setDurationInDays(100);

            try {
                Course searchCourse = courseService.findCourseByName(course.getName());
                System.out.println("Cannot pre-initialize course: " + course.getName());
            } catch (CourseNotFoundException e) {
                courseService.createCourse(course);
            }

            Course course1 = new Course();
            course1.setName("Science");
            course1.setSchool(searchSchool);
            course1.setStartDate(LocalDate.now());
            course1.setDurationInDays(150);

            try {
                Course searchCourse = courseService.findCourseByName(course1.getName());
                System.out.println("Cannot pre-initialize course: " + course1.getName());
            } catch (CourseNotFoundException e) {
                courseService.createCourse(course1);
            }
        } catch (SchoolNotFoundException e) {
            System.out.println("Cannot pre-initialize course! Reason:  " + e.getLocalizedMessage());
        }
    }

    private void initTeacher() {
        System.out.println("Starting teacher initialization...");

        try {
            Course course = courseService.findCourseByName("Mathematics");
            Course course1 = courseService.findCourseByName("Science");

            Teacher teacher = new Teacher();
            teacher.setName("Vinod John");
            teacher.setEmail("vinod@gmail.com");
            teacher.setGender(Gender.MALE);
            teacher.setSpecializedCourses(Arrays.asList(course, course1));

            try {
                Teacher searchTeacher = teacherService.findTeacherByName(teacher.getName());
                System.out.println("Cannot pre-initialize teacher: " + teacher.getName());
            } catch (TeacherNotFoundException e) {
                teacherService.createTeacher(teacher);
            }

        } catch (CourseNotFoundException e) {
            System.out.println("Cannot pre-initialize teacher! Reason:  " + e.getLocalizedMessage());
        }
    }

    private void initStudent() {
        System.out.println("Starting student initialization...");

        try {
            Course course = courseService.findCourseByName("Mathematics");

            Student student = new Student();
            student.setName("Hulk");
            student.setGender(Gender.MALE);
            student.setEmail("hulk@online.ee");
            student.setAge(45);
            student.setGrade(4);
            student.setCourses(Collections.singletonList(course));

            try {
                Student searchStudent = studentService.findStudentByName(student.getName());
                System.out.println("Cannot pre-initialize student: " + student.getName());
            } catch (StudentNotFoundException e) {
                studentService.createStudent(student);
            }

        } catch (CourseNotFoundException e) {
            System.out.println("Cannot pre-initialize student! Reason:  " + e.getLocalizedMessage());
        }
    }
}

