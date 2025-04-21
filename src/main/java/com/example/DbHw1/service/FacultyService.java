package com.example.DbHw1.service;

import com.example.DbHw1.module.Faculty;
import com.example.DbHw1.module.Student;
import com.example.DbHw1.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    @Autowired
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(Long id) {
        return facultyRepository.findById(id).orElse(null);
    }

    public Faculty updateFaculty(Long id, Faculty faculty) {
        faculty.setId(id);
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    // другие методы
    public List<Faculty> searchFaculties(String query) {
        return facultyRepository.findByNameIgnoreCaseOrColorIgnoreCase(query, query);
    }

    public List<Student> getFacultyStudents(Long facultyId) {
        return facultyRepository.findById(facultyId)
                .map(Faculty::getStudents)
                .orElse(Collections.emptyList());
    }
}
