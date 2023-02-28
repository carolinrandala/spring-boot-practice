package com.sda.study.springbootpractice.services.implementations;

import com.sda.study.springbootpractice.exceptions.CourseNotFoundException;
import com.sda.study.springbootpractice.exceptions.SchoolNotFoundException;
import com.sda.study.springbootpractice.models.Course;
import com.sda.study.springbootpractice.models.School;
import com.sda.study.springbootpractice.repositories.SchoolRepository;
import com.sda.study.springbootpractice.services.CourseService;
import com.sda.study.springbootpractice.services.SchoolService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of SchoolService
 *
 */
@Service
@Transactional // it will make database operations smoother

public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository; // we are going to use it in our methods to make operations

    @Autowired
    private CourseService courseService;

    @Override
    public void createSchool(School school) {
        school.setActive(true);
        schoolRepository.save(school); // saves a new record in database

    }
    @Override
    public School findSchoolById(Long id) throws SchoolNotFoundException {
        Optional<School> optionalSchool = schoolRepository.findById(id);

        if(optionalSchool.isEmpty()) {
            throw new SchoolNotFoundException(id);
        }
        return optionalSchool.get();
    }

    @Override
    public School findSchoolByName(String name) throws SchoolNotFoundException {
        Optional<School> schoolOptional = schoolRepository.findByName(name);

        if (schoolOptional.isEmpty()) {
            throw new SchoolNotFoundException(name);
        }
        return schoolOptional.get();
    }

    @Override
    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public void updateSchool(School school) throws SchoolNotFoundException {
        if (findSchoolById(school.getId()) != null) {
            schoolRepository.saveAndFlush(school); // save and flush to update data immediately to the database
        }
    }

    @Override
        public void deleteSchoolById(Long id) throws SchoolNotFoundException, CourseNotFoundException {
            School school = findSchoolById(id);
            // schoolRepository.delete(school); // To delete the record completely from the repo
            school.setActive(false);
            schoolRepository.saveAndFlush(school);

            // Find all the course belong to the school and delete the respective courses
            for (Course course: courseService.findAllCoursesBySchool(school)) {
                courseService.deleteCourseById(course.getId());
            }
        }

        @Override
            public void restoreSchoolById(Long id) throws SchoolNotFoundException, CourseNotFoundException {
                School school = findSchoolById(id);
                school.setActive(true);
                schoolRepository.saveAndFlush(school);

                // Find all the course belong to the school and restore the respective courses
                for (Course course: courseService.findAllCoursesBySchool(school)) {
                    courseService.restoreCourseById(course.getId());
                }
            }
        }
