package com.example.DbHw1.module;

import jakarta.persistence.*;
import org.springframework.data.annotation.Id;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    // геттеры, сеттеры и другие поля

    public void setId(Long id) {
    }

    public Faculty getFaculty() {
        return this.faculty;
    }
}