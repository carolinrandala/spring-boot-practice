package com.sda.study.springbootpractice.repositories;


import com.sda.study.springbootpractice.models.School;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository to handle all School related DB operations
 */
public interface SchoolRepository extends JpaRepository<School, Long> {
    Optional<School> findByName(String name);
}
