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

    public Student(long id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // геттеры, сеттеры и другие поля

    public void setId(Long id) {
    }

    public Faculty getFaculty() {
        return this.faculty;
    }
}