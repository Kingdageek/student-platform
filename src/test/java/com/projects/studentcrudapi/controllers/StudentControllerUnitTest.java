package com.projects.studentcrudapi.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.projects.studentcrudapi.dtos.StudentDto;
import com.projects.studentcrudapi.entities.Student;
import com.projects.studentcrudapi.services.StudentService;

@WebMvcTest(StudentController.class)
public class StudentControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    public void shouldCreateStudent() throws Exception {
        mockMvc.perform(post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Nonso\", \"address\": \"Johann fleck strasse\"}"))
                .andExpect(status().isCreated());
        // .andExpect(jsonPath("$.id").isNotEmpty())
        // .andExpect(jsonPath("$.name").value("Nonso"))
        // .andExpect(jsonPath("address").value("Johann fleck strasse"))
        // .andExpect(jsonPath("$.created_at").isNotEmpty())
        // .andExpect(jsonPath("$.updated_at").isNotEmpty());
    }

    @Test
    public void verify_createStudentSerialization() throws Exception {
        StudentDto student = new StudentDto(1L, "Nonso", "Johann fleck strasse", LocalDateTime.now(), LocalDateTime.now());
        Student savedStudent = student.toEntity();
        savedStudent.setId(student.getId());
        savedStudent.setCreatedAt(student.getCreatedAt());
        savedStudent.setUpdatedAt(student.getUpdatedAt());
        when(studentService.createStudent(student)).thenReturn(savedStudent);

        mockMvc.perform(post("/students").contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Nonso\", \"address\": \"Johann fleck strasse\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Nonso"))
                .andExpect(jsonPath("address").value("Johann fleck strasse"))
                .andExpect(jsonPath("$.created_at").isNotEmpty())
                .andExpect(jsonPath("$.updated_at").isNotEmpty());
    }
}
