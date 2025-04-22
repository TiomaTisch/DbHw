package com.example.DbHw1.TestRestTemplate;

import com.example.DbHw1.module.Faculty;
import com.example.DbHw1.repository.FacultyRepository;
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
public class FacultyControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FacultyRepository facultyRepository;

    @AfterEach
    void tearDown() {
        facultyRepository.deleteAll();
    }

    @Test
    public void createFaculty_ReturnsCreatedFaculty() {
        Faculty faculty = new Faculty();
        faculty.setName("Gryffindor");
        faculty.setColor("Scarlet");

        ResponseEntity<Faculty> response = restTemplate.postForEntity(
                "/faculty",
                faculty,
                Faculty.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getId());
        assertEquals("Gryffindor", response.getBody().getName());
    }

    @Test
    public void searchFaculties_ReturnsMatchingFaculties() {
        facultyRepository.save(new Faculty("Gryffindor", "Scarlet"));
        facultyRepository.save(new Faculty("Slytherin", "Green"));

        ResponseEntity<Faculty[]> response = restTemplate.getForEntity(
                "/faculty/search?query=Gryffindor",
                Faculty[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().length);
        assertEquals("Gryffindor", response.getBody()[0].getName());
    }
}