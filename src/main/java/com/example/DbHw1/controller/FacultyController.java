package com.example.DbHw1.controller;

import com.example.DbHw1.module.Faculty;
import com.example.DbHw1.module.Student;
import com.example.DbHw1.service.FacultyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public class FacultyController {

    private FacultyService facultyService;

    @GetMapping("/search")
    public List<Faculty> searchFaculties(@RequestParam String query) {
        return facultyService.searchFaculties(query);
    }

    @GetMapping("/{id}/students")
    public List<Student> getFacultyStudents(@PathVariable Long id) {
        return facultyService.getFacultyStudents(id);
    }
}