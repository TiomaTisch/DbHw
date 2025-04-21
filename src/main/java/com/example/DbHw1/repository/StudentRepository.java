package com.example.DbHw1.repository;

import com.example.DbHw1.module.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByAgeBetween(int min, int max);
}