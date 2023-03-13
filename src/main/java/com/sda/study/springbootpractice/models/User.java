package com.sda.study.springbootpractice.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * User model
 * for spring security
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Table(name = "users") // because user is keyword in postgres
public class User extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userName;
    private String password;
    @OneToOne(cascade = CascadeType.MERGE)
    private Authority authority;

    private boolean isActive;
}
