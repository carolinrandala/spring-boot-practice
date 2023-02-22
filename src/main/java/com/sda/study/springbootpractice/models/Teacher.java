package com.sda.study.springbootpractice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Teacher model
 */
@Entity
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> specializedCourses;

}
