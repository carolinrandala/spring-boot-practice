package com.sda.study.springbootpractice.repositories;

import com.sda.study.springbootpractice.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository to handle all Student related DB operations
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

}
