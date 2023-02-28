package com.sda.study.springbootpractice.services.implementations;

import com.sda.study.springbootpractice.exceptions.CourseNotFoundException;
import com.sda.study.springbootpractice.models.Course;
import com.sda.study.springbootpractice.models.School;
import com.sda.study.springbootpractice.repositories.CourseRepository;
import com.sda.study.springbootpractice.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public void createCourse(Course course) {
        course.setActive(true);
        courseRepository.save(course);
    }

    @Override
    public Course findCourseById(Long id) throws CourseNotFoundException {
        Optional<Course> courseOptional = courseRepository.findById(id);

        if(courseOptional.isEmpty()) {
            throw new CourseNotFoundException(id);
        }

        return courseOptional.get();
    }

    @Override
    public Course findCourseByName(String name) throws CourseNotFoundException {
        Optional<Course> courseOptional = courseRepository.findByName(name);

        if(courseOptional.isEmpty()) {
            throw new CourseNotFoundException(name);
        }

        return courseOptional.get();
    }

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAllCoursesBySchool(School school) {
        return courseRepository.findAllBySchool(school);
    }

    @Override
    public void updateCourse(Course course) throws CourseNotFoundException {
        if(findCourseById(course.getId()) != null) {
            courseRepository.saveAndFlush(course);
        }
    }

    @Override
    public void deleteCourseById(Long id) throws CourseNotFoundException {
        Course course = findCourseById(id);
        // courseRepository.delete(course); // To delete the record completely from the repo
        course.setActive(false);
        courseRepository.saveAndFlush(course);
    }

    @Override
    public void restoreCourseById(Long id) throws CourseNotFoundException {
        Course course = findCourseById(id);
        course.setActive(true);
        courseRepository.saveAndFlush(course);
    }
}
