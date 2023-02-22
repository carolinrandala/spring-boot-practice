package com.sda.study.springbootpractice.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * Student model
 */
@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private float grade;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Course> courses;
}
