package com.example.DbHw1.WebMvcTest;
import com.example.DbHw1.controller.FacultyController;
import com.example.DbHw1.module.Faculty;
import com.example.DbHw1.service.FacultyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;


import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(FacultyController.class)
class FacultyControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private FacultyService facultyService;

    @InjectMocks
    private FacultyController facultyController;

    @Test
    void getFaculty_ReturnsFacultyJson() throws Exception {
        Faculty faculty = new Faculty(1L, "Gryffindor", "Scarlet");
        when(facultyService.findFaculty(1L)).thenReturn(faculty);

        mockMvc.perform(get("/faculty/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gryffindor"))
                .andExpect(jsonPath("$.color").value("Scarlet"));
    }

    @Test
    void searchFaculties_ReturnsMatchingFaculties() throws Exception {
        List<Faculty> faculties = List.of(
                new Faculty(1L, "Gryffindor", "Scarlet")
        );
        when(facultyService.searchFaculties("Gryffindor")).thenReturn(faculties);

        mockMvc.perform(get("/faculty/search?query=Gryffindor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Gryffindor"))
                .andExpect(jsonPath("$[0].color").value("Scarlet"));
    }
}