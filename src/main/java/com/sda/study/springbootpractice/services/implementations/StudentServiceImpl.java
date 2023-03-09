package com.sda.study.springbootpractice.services.implementations;

import com.sda.study.springbootpractice.exceptions.StudentNotFoundException;
import com.sda.study.springbootpractice.models.School;
import com.sda.study.springbootpractice.models.Student;
import com.sda.study.springbootpractice.repositories.StudentRepository;
import com.sda.study.springbootpractice.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void createStudent(Student student) {
        student.setActive(true);
        studentRepository.save(student);
    }

    @Override
    public Student findStudentById(Long id) throws StudentNotFoundException {
        Optional<Student> studentOptional = studentRepository.findById(id);

        if(studentOptional.isEmpty()) {
            throw new StudentNotFoundException(id);
        }

        return studentOptional.get();
    }

    @Override
    public Student findStudentByName(String name) throws StudentNotFoundException {
        Optional<Student> studentOptional = studentRepository.findByName(name);

        if(studentOptional.isEmpty()) {
            throw new StudentNotFoundException(name);
        }

        return studentOptional.get();
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void updateStudent(Student student) throws StudentNotFoundException {
        if(findStudentById(student.getId()) != null) {
            studentRepository.saveAndFlush(student);
        }
    }

    @Override
    public void deleteStudentById(Long id) throws StudentNotFoundException {
        Student student = findStudentById(id);
        // studentRepository.delete(student); // To delete the record completely from the repo
        student.setActive(false);
        studentRepository.saveAndFlush(student);
    }

    @Override
    public void restoreStudentById(Long id) throws StudentNotFoundException {
        Student student = findStudentById(id);
        student.setActive(true);
        studentRepository.saveAndFlush(student);
    }
}