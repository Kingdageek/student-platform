package com.projects.studentcrudapi.dtos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

import com.projects.studentcrudapi.entities.Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StudentDto {
    private long id;
    @NotBlank(message = "Student name is required")
    private String name;
    private String address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Student toEntity() {
        return new Student(name, address);
    }
}
