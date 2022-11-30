package com.projects.studentcrudapi.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projects.studentcrudapi.dtos.StudentDto;
import com.projects.studentcrudapi.entities.Student;
import com.projects.studentcrudapi.services.StudentService;
import com.projects.studentcrudapi.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudent(long id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()) {
            return null;
        }
        return optionalStudent.get();
    }

    @Override
    public Student createStudent(StudentDto student) {
        return studentRepository.save(student.toEntity());
    }

    @Override
    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(StudentDto student, long id) {
        Student prevStudent = getStudent(id);
        prevStudent.setName(student.getName());
        prevStudent.setAddress(student.getAddress());
        return studentRepository.save(prevStudent);
    }

}
