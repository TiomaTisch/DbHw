package com.example.DbHw1.WebMvcTest;

import com.example.DbHw1.controller.StudentController;
import com.example.DbHw1.module.Student;
import com.example.DbHw1.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(StudentController.class)
public class StudentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    public void getStudent_ReturnsStudentJson() throws Exception {
        Student student = new Student(1L, "Harry Potter", 11);
        when(studentService.findStudent(1L)).thenReturn(student);

        mockMvc.perform(get("/student/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Harry Potter"))
                .andExpect(jsonPath("$.age").value(11));
    }

    @Test
    public void createStudent_ReturnsCreatedStatus() throws Exception {
        Student student = new Student("Ron Weasley", 11);
        Student savedStudent = new Student(1L, "Ron Weasley", 11);

        when(studentService.createStudent(any(Student.class))).thenReturn(savedStudent);

        mockMvc.perform(post("/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Ron Weasley\",\"age\":11}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }
}
