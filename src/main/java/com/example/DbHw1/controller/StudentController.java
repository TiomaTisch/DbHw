package com.example.DbHw1.controller;

import com.example.DbHw1.module.Faculty;
import com.example.DbHw1.module.Student;
import com.example.DbHw1.repository.StudentRepository;
import com.example.DbHw1.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class StudentController {

    private StudentService studentService;

    @GetMapping("/by-age")
    public List<Student> getStudentsByAgeRange(
            @RequestParam int min,
            @RequestParam int max
    ) {
        return studentService.findByAgeBetween(min, max);
    }

    @GetMapping("/{id}/faculty")
    public Faculty getStudentFaculty(@PathVariable Long id) {
        return studentService.getStudentFaculty(id);
    }
}