package com.projects.studentcrudapi.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.projects.studentcrudapi.dtos.StudentDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "students")
@RequiredArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Student(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public StudentDto toDto() {
        return StudentDto.builder()
                .id(this.id)
                .name(this.name)
                .address(this.address)
                .createdAt(this.createdAt)
                .updatedAt(this.updatedAt)
                .build();
    }
}
