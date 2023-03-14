package com.sda.study.springbootpractice.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Student model
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Student extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String email;
    private float grade;
    @OneToMany(cascade = CascadeType.MERGE)
    private List<Course> courses;

    private boolean isActive;
}
