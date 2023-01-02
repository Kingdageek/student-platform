package com.projects.studentcrudapi.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

import com.projects.studentcrudapi.dtos.StudentDto;
import com.projects.studentcrudapi.entities.Student;
import com.projects.studentcrudapi.repositories.StudentRepository;
import com.projects.studentcrudapi.services.impl.StudentServiceImpl;

// @ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
    // unit test must be independent of the SB framework -> No fetching of beans
    // from APP context
    // @Mock
    private StudentRepository studentRepo;

    // @InjectMocks
    private StudentServiceImpl studentService;

    @BeforeEach
    public void setup() {
        // Arrange
        // MockitoExtension can be used directly instead of this
        studentRepo = mock(StudentRepository.class);
        studentService = new StudentServiceImpl(studentRepo);

        Student studentEntity = Student.builder()
                .id(1L)
                .name("Stu Dent")
                .address("Dorm 2")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(studentRepo.findById(1L))
                .thenReturn(Optional.of(studentEntity));

        when(studentRepo.findAll())
                .thenReturn(List.of(studentEntity));
    }

    @Test
    void testCreateStudent() {
        // Arrange
        StudentDto student = StudentDto.builder()
                .name("Stu Dent")
                .address("Dorm 2")
                .build();
        Student studentEntity = Student.builder()
                .id(1L)
                .name("Stu Dent")
                .address("Dorm 2")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        when(studentRepo.save(student.toEntity()))
                .thenReturn(studentEntity);
        // Act
        Student stud = studentService.createStudent(student);
        // Assert
        assertEquals("Stu Dent", stud.getName());
        assertEquals("Dorm 2", stud.getAddress());
        assertEquals(1L, stud.getId());
        assertNotNull(stud.getCreatedAt());
        assertNotNull(stud.getUpdatedAt());
    }

    @Test
    void testGetAllStudents() {
        // Act
        List<Student> students = studentService.getAllStudents();
        // Assert
        assertNotNull(students);
        assertEquals("Stu Dent", students.get(0).getName());
    }

    @Test
    void testGetStudent() {
        // Act
        Student stud = studentService.getStudent(1L);
        // Assert
        assertEquals("Stu Dent", stud.getName());
        assertEquals("Dorm 2", stud.getAddress());
        assertEquals(1L, stud.getId());
        assertNotNull(stud.getCreatedAt());
        assertNotNull(stud.getUpdatedAt());
    }

    @Test
    void testUpdateStudent() {
        // Arrange
        StudentDto student2 = StudentDto.builder()
                .name("Stud Ent")
                .address("Dorm 1")
                .build();

        Student studentEntity2 = studentService.getStudent(1L);
        studentEntity2.setName(student2.getName());
        studentEntity2.setAddress(student2.getAddress());
        // studentEntity2.setUpdatedAt(LocalDateTime.now());

        when(studentRepo.save(studentEntity2))
                .thenReturn(studentEntity2);

        // Act
        Student stud = studentService.updateStudent(student2, 1L);

        assertEquals("Stud Ent", stud.getName());
        assertEquals("Dorm 1", stud.getAddress());
        assertEquals(1L, stud.getId());
        assertEquals(studentEntity2.getCreatedAt(), stud.getCreatedAt());
        assertNotNull(stud.getUpdatedAt());
    }
}
