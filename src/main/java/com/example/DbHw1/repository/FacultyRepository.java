package com.example.DbHw1.repository;

import com.example.DbHw1.module.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyRepository extends JpaRepository<Faculty, Long> {
}