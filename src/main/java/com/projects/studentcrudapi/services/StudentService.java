package com.projects.studentcrudapi.services;

import com.projects.studentcrudapi.dtos.StudentDto;
import com.projects.studentcrudapi.entities.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student getStudent(long id);

    Student createStudent(StudentDto student);

    void deleteStudent(long id);

    Student updateStudent(StudentDto student, long id);
}
