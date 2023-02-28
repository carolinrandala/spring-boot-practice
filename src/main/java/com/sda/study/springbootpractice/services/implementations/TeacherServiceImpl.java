package com.sda.study.springbootpractice.services.implementations;

import com.sda.study.springbootpractice.exceptions.TeacherNotFoundException;
import com.sda.study.springbootpractice.models.Teacher;
import com.sda.study.springbootpractice.repositories.TeacherRepository;
import com.sda.study.springbootpractice.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public void createTeacher(Teacher teacher) {
        teacher.setActive(true);
        teacherRepository.save(teacher);
    }

    @Override
    public Teacher findTeacherById(Long id) throws TeacherNotFoundException {
        Optional<Teacher> teacherOptional = teacherRepository.findById(id);

        if(teacherOptional.isEmpty()) {
            throw new TeacherNotFoundException(id);
        }

        return teacherOptional.get();
    }

    @Override
    public Teacher findTeacherByName(String name) throws TeacherNotFoundException {
        Optional<Teacher> teacherOptional = teacherRepository.findByName(name);

        if(teacherOptional.isEmpty()) {
            throw new TeacherNotFoundException(name);
        }

        return teacherOptional.get();
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void updateTeacher(Teacher teacher) throws TeacherNotFoundException {
        if(findTeacherById(teacher.getId()) != null) {
            teacherRepository.saveAndFlush(teacher);
        }
    }

    @Override
    public void deleteTeacherById(Long id) throws TeacherNotFoundException {
        Teacher teacher = findTeacherById(id);
        // teacherRepository.delete(teacher); // To delete the record completely from the repo
        teacher.setActive(false);
        teacherRepository.saveAndFlush(teacher);
    }

    @Override
    public void restoreTeacherById(Long id) throws TeacherNotFoundException {
        Teacher teacher = findTeacherById(id);
        teacher.setActive(true);
        teacherRepository.saveAndFlush(teacher);
    }
}