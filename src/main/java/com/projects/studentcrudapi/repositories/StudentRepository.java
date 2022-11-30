package com.projects.studentcrudapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projects.studentcrudapi.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
