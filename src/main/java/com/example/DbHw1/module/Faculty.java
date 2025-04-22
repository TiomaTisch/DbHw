package com.example.DbHw1.module;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;

    @OneToMany(mappedBy = "faculty")
    private List<Student> students;

    public Faculty(String name, String color) {
        this.name = name;
        this.color = color;
    }
    public Faculty() {

    }

    // геттеры, сеттеры и другие поля

    public void setId(Long id) {
        this.id = id;
    }

    public List<Student> getStudents() {
        return this.students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Object getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }
}