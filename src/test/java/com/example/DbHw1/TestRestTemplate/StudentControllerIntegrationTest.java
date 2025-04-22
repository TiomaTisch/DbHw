package com.example.DbHw1.TestRestTemplate;

import com.example.DbHw1.module.Student;
import com.example.DbHw1.repository.StudentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudentControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private StudentRepository studentRepository;

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    public void createStudent_ReturnsCreatedStudent() {
        Student student = new Student();
        student.setName("Harry Potter");
        student.setAge(11);

        ResponseEntity<Student> response = restTemplate.postForEntity(
                "/student",
                student,
                Student.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Harry Potter", response.getBody().getName());
    }

    @Test
    public void getStudent_ReturnsStudent() {
        Student savedStudent = studentRepository.save(new Student("Hermione Granger", 12));

        ResponseEntity<Student> response = restTemplate.getForEntity(
                "/student/" + savedStudent.getId(),
                Student.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Hermione Granger", response.getBody().getName());
    }

    @Test
    public void getStudentsByAge_ReturnsFilteredList() {
        studentRepository.save(new Student("Ron Weasley", 11));
        studentRepository.save(new Student("Draco Malfoy", 12));

        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                "/student/age-between?min=11&max=12",
                Student[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().length);
    }
}