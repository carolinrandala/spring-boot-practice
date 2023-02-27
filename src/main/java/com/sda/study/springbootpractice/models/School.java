package com.sda.study.springbootpractice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

/**
 * School model
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data // it will create getters and setters
public class School extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String address;
    private String phone;
    private boolean isActive;
}
