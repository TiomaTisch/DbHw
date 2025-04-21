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

    // геттеры, сеттеры и другие поля

    public void setId(Long id) {
    }

    public List<Student> getStudents() {
        return this.students;
    }
}