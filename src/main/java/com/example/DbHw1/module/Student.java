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

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public Student() {

    }

    // геттеры, сеттеры и другие поля


    public void setId(Long id) {
        this.id = id;
    }

    public Faculty getFaculty() {
        return this.faculty;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age ) {
        this.age = age;
    }
}